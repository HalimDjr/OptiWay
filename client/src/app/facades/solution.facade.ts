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
            const bData = best.data;
            const cData = current.data;
            if (cData.nbCommandesLivrees > bData.nbCommandesLivrees) return current;
            if (cData.nbCommandesLivrees === bData.nbCommandesLivrees &&
                cData.tempsTotal < bData.tempsTotal) return current;
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

        for (const result of results) {
            for (const route of result.routes) {
                const commandesIds = route.steps
                    .filter(step => step.type === 'job')
                    .map(step => adresses[(step as any).id].numeroCommande)
                    .filter(Boolean);

                const tourneeRequest = {
                    idTournee: Math.floor(Math.random() * 2000000000),
                    tempsTotal: route.duration,
                    heureDepart: new Date().toISOString(),
                    distanceTotale: route.cost,
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