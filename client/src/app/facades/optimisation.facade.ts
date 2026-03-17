import { Injectable, inject, signal } from '@angular/core';
import { LatLngTuple } from 'leaflet';
import { Adresse } from '../data/adresse';
import { OptimizationResult } from '../services/OptimizationResult';
import { Carto } from '../services/carto';
import { Sweep } from '../services/optimisation-sweep.service';
import { OptimizeAdvancedService } from '../services/optimisation-clusters';
import { Matrice } from '../data/Matrice';

@Injectable({ providedIn: 'root' })
export class OptimisationFacade {
    private readonly _carto = inject(Carto);
    private readonly _sweep = inject(Sweep);
    private readonly _cluster = inject(OptimizeAdvancedService);

    async optimiserEquitable(
        adresses: readonly Adresse[],
        nbVehicules: number,
        maxTime: number
    ): Promise<{ results: OptimizationResult[], routes: ReadonlyArray<ReadonlyArray<LatLngTuple>> }> {
        const results = await this._carto.optimizeAndExport(adresses, nbVehicules, maxTime);
        const allRoutes = await Promise.all(
            results.flatMap(r =>
                r.routes.map(route =>
                    this._carto.getDirections(route.steps.map(s => s.location))
                )
            )
        );
        return { results, routes: allRoutes };
    }

    async optimiserCluster(
        adresses: readonly Adresse[],
        nbVehicules: number,
        maxTime: number,
        matrix?: Matrice
    ): Promise<{ results: OptimizationResult[], routes: ReadonlyArray<ReadonlyArray<LatLngTuple>> }> {
        const parking = adresses.at(-1)!;
        const deliveries = adresses.slice(0, -1);
        const useSimple = deliveries.length <= 50 && nbVehicules <= 3;

        if (useSimple) {
            const optimizedRoute = await this._carto.optimize({
                nbVehicules, maxTimePerVehicule: maxTime,
                adresses: deliveries, parking
            });
            const allDirections: ReadonlyArray<LatLngTuple>[] = [];
            for (const route of optimizedRoute.routes) {
                const d = await this._carto.getDirections(route.steps.map(s => s.location));
                allDirections.push([...d] as LatLngTuple[]);
            }
            return { results: [optimizedRoute], routes: allDirections };
        }

        if (!matrix) throw new Error('Matrice non disponible');

        const result = await this._cluster.optimizeAdvanced({
            nbVehicules, maxTimePerVehicule: maxTime,
            adresses: deliveries, parking,
            preCalculatedMatrix: { distances: matrix.distances, durations: matrix.durations }
        });

        const allDirections: ReadonlyArray<LatLngTuple>[] = [];
        for (const r of result.results) {
            if (r.routes.length > 0) {
                const d = await this._carto.getDirections(r.routes[0].steps.map(s => s.location));
                allDirections.push([...d] as LatLngTuple[]);
            }
        }
        return { results: result.results, routes: allDirections };
    }

    async afficherDepuisBackend(
        solution: any,
        parking: Adresse
    ): Promise<ReadonlyArray<ReadonlyArray<LatLngTuple>>> {
        const allDirections: ReadonlyArray<LatLngTuple>[] = [];
        for (const tournee of solution.tournees) {
            if (!tournee.commandes || tournee.commandes.length === 0) continue;
            const locations: [number, number][] = [
                [parking.lng, parking.lat],
                ...tournee.commandes.map((cmd: any) =>
                    [cmd.longtitude, cmd.latitude] as [number, number]
                ),
                [parking.lng, parking.lat]
            ];
            try {
                const d = await this._carto.getDirections(locations);
                allDirections.push([...d] as LatLngTuple[]);
            } catch (err) {
                console.error('Erreur directions:', err);
            }
        }
        return allDirections;
    }
}