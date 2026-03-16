import { Component, computed, effect, inject, Signal, signal, WritableSignal } from '@angular/core';
import { LeafletModule } from '@bluehalo/ngx-leaflet';
import { latLng, LatLngBoundsLiteral, LatLngTuple, Layer, MapOptions, polyline, Rectangle, rectangle, tileLayer } from 'leaflet';
import { Carto } from './services/carto';
import { getMarker } from './utils/marker';
import { FormsModule } from '@angular/forms';
import { Adresse } from './data/adresse';
import { OptimizationResult, RouteStepBaseJob } from './services/OptimizationResult';
import { Injector } from '@angular/core';
import { ApiService } from './services/api.service';
import { CommonModule } from '@angular/common';
import { optimiseEquitable } from './services/optimisation-algo.service';
import { firstValueFrom } from 'rxjs';

const lastOptimizationResponseKey = "lastOptimizationResponse";

@Component({
    selector: 'app-root-test',
    imports: [FormsModule, LeafletModule, CommonModule],
    templateUrl: './appTesApi.html',
    styleUrl: './app.scss'
})
export class App {
    private readonly _srvCarto = inject(Carto);
    private readonly injector = inject(Injector);
    private readonly _apiService = inject(ApiService);

    // =============================================
    // PARKING FIXE (toujours le dernier point)
    // =============================================
    private readonly PARKING: Adresse = {
        name: 'Parking',
        lat: 45.188529,
        lng: 5.724524
    };

    // =============================================
    // STATE
    // =============================================
    private readonly bounds = signal<LatLngBoundsLiteral>([[45.1, 5.6], [45.3, 5.9]]);

    protected readonly options: MapOptions = {
        zoom: 11,
        center: latLng(45.188529, 5.724524),
    };

    private readonly _adresses = signal<readonly Adresse[]>([]);
    private readonly _optimizationResult: WritableSignal<undefined | OptimizationResult>;
    private readonly _routes = signal<ReadonlyArray<ReadonlyArray<LatLngTuple>>>([]);

    // Données depuis la BD
    protected commandesNonPlanifiees = signal<any[]>([]);
    protected nbEquipes = signal<number>(0);
    protected tournees = signal<any[]>([]);

    // Résultats d'optimisation en attente de sauvegarde
    private _optimisationResults = signal<OptimizationResult[]>([]);

    // États UI
    protected isLoadingCommandes = signal(false);
    protected isLoadingOptimisation = signal(false);
    protected isLoadingSauvegarde = signal(false);
    protected isLoadingTournees = signal(false);
    protected messageStatut = signal<string>('');
    protected optimisationFaite = signal(false);

    protected readonly layers: Signal<Layer[]>;

    private readonly colors = [
        '#FF0000', '#00FF00', '#0000FF', '#FFFF00', '#FF00FF',
        '#00FFFF', '#FF8800', '#8800FF', '#00FF88', '#FF0088',
        '#88FF00', '#0088FF', '#FF4400', '#4400FF', '#00FF44',
        '#FF0044', '#44FF00', '#0044FF', '#FFAA00', '#AA00FF',
    ];

    constructor() {
        const back = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', { maxZoom: 18, attribution: '...' });
        const bboxRectangle: Signal<Rectangle> = computed<Rectangle>(
            () => rectangle(this.bounds(), { color: 'blue', weight: 1 })
        );
        this.layers = computed<Layer[]>(
            () => [
                back,
                bboxRectangle(),
                // Afficher le parking en noir, les autres en bleu
                ...this._adresses().map((a, i) =>
                    getMarker(a, a.name === 'Parking' ? 'black' : 'blue')
                ),
                ...this._routes().map((r, i) => polyline([...r], {
                    color: this.colors[i % this.colors.length],
                    weight: 4,
                    opacity: 0.8,
                    smoothFactor: 1
                }))
            ]
        );

        const lastOptStr = localStorage.getItem(lastOptimizationResponseKey);
        this._optimizationResult = signal<undefined | OptimizationResult>(
            lastOptStr && lastOptStr !== "undefined" ? JSON.parse(lastOptStr) : undefined
        );

        effect(() => {
            const opt = this._optimizationResult();
            localStorage.setItem(lastOptimizationResponseKey, JSON.stringify(opt));
        });
    }

    // =============================================
    // TODAY HELPER
    // =============================================
    protected today(): string {
        return new Date().toISOString().split('T')[0];
    }

    // =============================================
    // ETAPE 1 : CHARGER COMMANDES NON PLANIFIEES
    // =============================================
    protected chargerCommandesNonPlanifiees(): void {
        this.isLoadingCommandes.set(true);
        this.messageStatut.set('Chargement des commandes...');
        this.optimisationFaite.set(false);
        this._routes.set([]);

        this._apiService.getCommandesNonLivrees().subscribe({
            next: (commandes: any[]) => {
                this.commandesNonPlanifiees.set(commandes);
                this.isLoadingCommandes.set(false);
                this.messageStatut.set(`✅ ${commandes.length} commandes non planifiées chargées`);

                // Construire les adresses depuis les commandes
                const adresses: Adresse[] = commandes
                    .filter((c: any) => c.latitude && c.longtitude)
                    .map((c: any) => ({
                        name: c.numeroCommande,
                        lat: c.latitude,
                        lng: c.longtitude
                    }));

                // ← Ajouter le parking fixe à la FIN (obligatoire pour l'algo)
                adresses.push(this.PARKING);

                this._adresses.set(adresses);
                console.log(`✅ ${adresses.length - 1} commandes + parking fixe affichés sur la carte`);
            },
            error: (err: any) => {
                this.isLoadingCommandes.set(false);
                this.messageStatut.set('❌ Erreur lors du chargement des commandes');
                console.error(err);
            }
        });

        // Charger le nombre d'équipes
        this._apiService.getNombreEquipes().subscribe({
            next: (nb: number) => {
                this.nbEquipes.set(nb);
                console.log(`✅ ${nb} équipes disponibles`);
            },
            error: (err: any) => console.error('Erreur équipes:', err)
        });
    }

    // =============================================
    // ETAPE 2A : OPTIMISER (affiche sur carte)
    // =============================================
    protected async optimiser(maxTimePerVehicule: number): Promise<void> {
        const adresses = this._adresses();
        const nbEquipes = this.nbEquipes();

        if (adresses.length <= 1) {
            this.messageStatut.set('⚠️ Chargez d\'abord les commandes');
            return;
        }

        if (nbEquipes === 0) {
            this.messageStatut.set('⚠️ Aucune équipe disponible');
            return;
        }

        this.isLoadingOptimisation.set(true);
        this.optimisationFaite.set(false);
        this._routes.set([]);

        // adresses.length - 1 car le parking ne compte pas
        this.messageStatut.set(`🔄 Optimisation en cours (${adresses.length - 1} commandes, ${nbEquipes} équipes)...`);

        try {
            const result = await optimiseEquitable(
                adresses,          // ← parking est le dernier élément
                nbEquipes,
                maxTimePerVehicule,
                (params) => this._srvCarto.optimize(params)
            );

            if (result.results.length === 0) {
                this.messageStatut.set(`❌ Optimisation échouée: ${result.stats.alerte}`);
                this.isLoadingOptimisation.set(false);
                return;
            }

            // Stocker les résultats pour la sauvegarde
            this._optimisationResults.set(result.results);

            // Afficher les routes sur la carte
// Afficher les routes sur la carte
            const allDirections: ReadonlyArray<LatLngTuple>[] = [];
            for (const routeResult of result.results) {
                for (const route of routeResult.routes) {
                    try {
                        const directions = await this._srvCarto.getDirections(
                            route.steps.map(s => s.location)
                        );
                        allDirections.push([...directions] as LatLngTuple[]);
                    } catch (err) {
                        console.warn('Directions non disponibles pour cette route, affichage en ligne droite');
                        // Fallback : ligne droite entre les points
                        const points = route.steps.map(s => [s.location[1], s.location[0]] as LatLngTuple);
                        allDirections.push(points);
                    }
                }
            }
            this._routes.set(allDirections);

            this.optimisationFaite.set(true);
            this.messageStatut.set(`✅ ${allDirections.length} tournées trouvées — vérifiez la carte puis sauvegardez`);

        } catch (err: any) {
            this.messageStatut.set('❌ Erreur lors de l\'optimisation');
            console.error(err);
        }

        this.isLoadingOptimisation.set(false);
    }

    // =============================================
    // ETAPE 2B : SAUVEGARDER EN BD
    // =============================================
    protected async sauvegarder(): Promise<void> {
        const adresses = this._adresses();
        const results = this._optimisationResults();

        if (results.length === 0) {
            this.messageStatut.set('⚠️ Lancez d\'abord l\'optimisation');
            return;
        }

        this.isLoadingSauvegarde.set(true);
        this.messageStatut.set('💾 Sauvegarde des tournées en base de données...');

        let idTournee = 100;
        let nbSauvegardees = 0;

        const commandesDejaUtilisees = new Set<string>();

        // 📅 Date du jour
        const today = new Date();
        const todayStr = today.toISOString().split('T')[0];
        const dateTourneeStr = `${todayStr}T00:00:00.000Z`;

        console.log(`📊 ${results.length} résultats ORS (1 par véhicule)`);

        for (let i = 0; i < results.length; i++) {
            const result = results[i];

            // Chaque résultat contient UNE route (puisqu'on a appelé avec nbVehicules=1)
            if (result.routes.length === 0) continue;

            const route = result.routes[0];

            const commandeIds = route.steps
                .filter((s): s is RouteStepBaseJob => s.type === 'job')
                .map(s => adresses[s.id]?.name)
                .filter((name): name is string => !!name && name !== 'Parking')
                .filter(name => !commandesDejaUtilisees.has(name));

            if (commandeIds.length === 0) {
                console.warn(`⚠️ Véhicule ${i+1}: sans commandes, ignoré`);
                continue;
            }

            commandeIds.forEach(id => commandesDejaUtilisees.add(id));

            // Heure de départ : 8h + i*30 minutes
            const heureDepart = `0${8 + Math.floor(i/2)}:${i%2 === 0 ? '00' : '30'}:00`;
            const heureDepartStr = `${todayStr}T${heureDepart}.000Z`;

            const tournee = {
                idTournee: idTournee++,
                tempsTotal: Math.round(route.duration / 3600 * 10) / 10,
                dateTournee: dateTourneeStr,
                heureDepart: heureDepartStr,
                distanceTotale: Math.round((route.cost / 1000) * 10) / 10,
                statutTournee: 2,
                commandes_ids: commandeIds,
                numeroEquipe: i + 1  // Équipe 1 pour le premier, 2 pour le second...
            };

            console.log(`💾 Véhicule ${i+1}: ${commandeIds.length} commandes...`);

            try {
                const res = await firstValueFrom(this._apiService.createTournee(tournee));
                nbSauvegardees++;
                console.log(`✅ Tournée ${res.idTournee} créée (équipe ${res.numeroEquipe})`);
            } catch (err) {
                console.error(`❌ Erreur véhicule ${i+1}:`, err);
            }
        }

        this.isLoadingSauvegarde.set(false);
        this.optimisationFaite.set(false);
        this._optimisationResults.set([]);

        this.messageStatut.set(`✅ ${nbSauvegardees}/${results.length} tournées sauvegardées !`);
    }

    // =============================================
    // ETAPE 3 : VOIR LES TOURNEES PAR DATE
    // =============================================
    protected voirTournees(date: string): void {
        this.isLoadingTournees.set(true);
        this.messageStatut.set('Chargement des tournées...');

        this._apiService.getTourneesByDate(date).subscribe({
            next: (tournees: any[]) => {
                this.tournees.set(tournees);
                this.isLoadingTournees.set(false);
                this.messageStatut.set(`✅ ${tournees.length} tournée(s) trouvée(s)`);
            },
            error: (err: any) => {
                this.isLoadingTournees.set(false);
                this.messageStatut.set('❌ Erreur lors du chargement des tournées');
                console.error(err);
            }
        });
    }
}