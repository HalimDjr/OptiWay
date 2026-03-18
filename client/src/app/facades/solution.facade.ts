import { Injectable, inject, signal } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { Adresse } from '../data/adresse';
import { OptimizationResult } from '../services/OptimizationResult';
import { ApiService } from '../services/api.service';

@Injectable({ providedIn: 'root' })
export class SolutionFacade {
    private readonly _api = inject(ApiService);

    private readonly PRIX_LITRE = 1.85;
    private readonly CONSOMMATION_100KM = 8;

    readonly solutionEquitable = signal<any | undefined>(undefined);
    readonly solutionSweep = signal<any | undefined>(undefined);
    readonly solutionCluster = signal<any | undefined>(undefined);
    readonly solutionModal = signal<any | undefined>(undefined);
    readonly modalOuvert = signal<boolean>(false);

    calculerCout(distanceKm: number): number {
        return Math.round(
            (distanceKm * this.CONSOMMATION_100KM / 100) * this.PRIX_LITRE * 100
        ) / 100;
    }

    ouvrirModal(solution: any): void {
        this.solutionModal.set(solution);
        this.modalOuvert.set(true);
    }

    fermerModal(): void {
        this.modalOuvert.set(false);
        this.solutionModal.set(undefined);
    }

    meilleureSolution(): string {
        const solutions = [
            { nom: 'EQUITABLE', data: this.solutionEquitable() },
            { nom: 'SWEEP',     data: this.solutionSweep() },
            { nom: 'CLUSTER',   data: this.solutionCluster() }
        ].filter(s => s.data !== undefined);

        if (solutions.length === 0) return '';

        return solutions.reduce((best, current) => {
            const b = best.data;
            const c = current.data;

            if (c.nbCommandesLivrees > b.nbCommandesLivrees) return current;
            if (c.nbCommandesLivrees < b.nbCommandesLivrees) return best;

            if (c.distanceTotale < b.distanceTotale) return current;
            if (c.distanceTotale > b.distanceTotale) return best;

            if (c.tempsTotal < b.tempsTotal) return current;
            if (c.tempsTotal > b.tempsTotal) return best;

            if (c.coutTotal < b.coutTotal) return current;
            return best;
        }).nom;
    }

    async sauvegarder(
        results: OptimizationResult[],
        adresses: readonly Adresse[],
        nomAlgorithme: string,
        equipes: any[]
    ): Promise<any> {
        const tourneeIds: number[] = [];
        let tourneeIndex = 0;

        // Calcul nbCommandesLivrees — compte les jobs directement
        let nbCommandesLivrees = 0;
        for (const result of results) {
            for (const route of result.routes) {
                nbCommandesLivrees += route.steps.filter(step => step.type === 'job').length;
            }
        }

        for (const result of results) {
            for (const route of result.routes) {
                const commandesIds = route.steps
                    .filter(step => step.type === 'job')
                    .map(step => adresses[(step as any).id]?.numeroCommande)
                    .filter(Boolean);
                console.log('equipes:', equipes);
                console.log('tourneeIndex:', tourneeIndex, '→ numeroEquipe:', equipes[tourneeIndex % equipes.length]?.numeroEquipe);
                const tourneeRequest = {
                    idTournee: Math.floor(Math.random() * 2000000000),
                    tempsTotal: route.duration,
                    heureDepart: new Date().toISOString(),
                    distanceTotale: (route.distance ?? 0) / 1000,
                    statutTournee: 2,
                    commandes_ids: commandesIds,
                    numeroEquipe: equipes[tourneeIndex % equipes.length].numeroEquipe
                };

                const tournee = await firstValueFrom(
                    this._api.createTournee(tourneeRequest)
                );
                tourneeIds.push(tournee.idTournee);
                tourneeIndex++;
            }
        }

        const solutionRequest = {
            nomAlgorithme,
            nbCommandesLivrees,
            nbEquipesUtilisees: tourneeIds.length,
            tournees_ids: tourneeIds
        };

        return firstValueFrom(this._api.saveSolution(solutionRequest));
    }

    async sauvegarderSweep(
        sweepResults: {result: OptimizationResult, adresses: readonly Adresse[]}[],
        nomAlgorithme: string,
        equipes: any[]
    ): Promise<any> {
        const tourneeIds: number[] = [];
        let tourneeIndex = 0;

        // Calcul nbCommandesLivrees — compte les jobs directement
        let nbCommandesLivrees = 0;
        for (const { result } of sweepResults) {
            for (const route of result.routes) {
                nbCommandesLivrees += route.steps.filter(step => step.type === 'job').length;
            }
        }

        for (const { result, adresses } of sweepResults) {
            for (const route of result.routes) {
                const commandesIds = route.steps
                    .filter(step => step.type === 'job')
                    .map(step => adresses[(step as any).id]?.numeroCommande)
                    .filter(Boolean);

                const tourneeRequest = {
                    idTournee: Math.floor(Math.random() * 2000000000),
                    tempsTotal: route.duration,
                    heureDepart: new Date().toISOString(),
                    distanceTotale: (route.distance ?? 0) / 1000,
                    statutTournee: 2,
                    commandes_ids: commandesIds,
                    numeroEquipe: equipes[tourneeIndex % equipes.length].numeroEquipe
                };

                const tournee = await firstValueFrom(
                    this._api.createTournee(tourneeRequest)
                );
                tourneeIds.push(tournee.idTournee);
                tourneeIndex++;
            }
        }

        const solutionRequest = {
            nomAlgorithme,
            nbCommandesLivrees,
            nbEquipesUtilisees: tourneeIds.length,
            tournees_ids: tourneeIds
        };

        return firstValueFrom(this._api.saveSolution(solutionRequest));
    }

    async valider(solutionId: number): Promise<void> {
        await firstValueFrom(this._api.activerSolution(solutionId));
        console.log(`Solution ${solutionId} validée`);
    }
}