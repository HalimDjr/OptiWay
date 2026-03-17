import { Injectable, inject, signal } from '@angular/core';
import { Adresse } from '../data/adresse';
import { Matrice } from '../data/Matrice';
import { Carto } from '../services/carto';

@Injectable({ providedIn: 'root' })
export class MatriceFacade {
    private readonly _carto = inject(Carto);
    private readonly CACHE_KEY = 'matrice_cache';

    readonly matrice = signal<Matrice | undefined>(undefined);

    async chargerMatrice(adresses: readonly Adresse[]): Promise<void> {
        const cached = this.getCached(adresses);
        if (cached) {
            this.matrice.set(cached);
            return;
        }

        const m = await this._carto.getDistanceMatrix(adresses);
        const matriceComplete: Matrice = {
            distances: m.distances,
            durations: m.durations,
            sources: m.sources,
            destinations: m.destinations
        };
        this.matrice.set(matriceComplete);
        this.setCache(adresses, matriceComplete);
    }

    private getCached(adresses: readonly Adresse[]): Matrice | null {
        try {
            const cached = localStorage.getItem(this.CACHE_KEY);
            if (!cached) return null;
            const { key, matrice } = JSON.parse(cached);
            if (key === this.buildKey(adresses)) {
                console.log('Matrice récupérée depuis le cache');
                return matrice;
            }
            return null;
        } catch { return null; }
    }

    private setCache(adresses: readonly Adresse[], matrice: Matrice): void {
        try {
            const key = this.buildKey(adresses);
            localStorage.setItem(this.CACHE_KEY, JSON.stringify({ key, matrice }));
            console.log('Matrice sauvegardée dans le cache');
        } catch { console.warn('Impossible de sauvegarder la matrice'); }
    }

    private buildKey(adresses: readonly Adresse[]): string {
        return adresses.map(a => `${a.lat},${a.lng}`).join('|');
    }
}