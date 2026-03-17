import { Component, computed, effect, inject, OnInit, Signal, signal, WritableSignal, Injector, runInInjectionContext } from '@angular/core';
import { LeafletModule } from '@bluehalo/ngx-leaflet';
import { latLng, LatLngBoundsLiteral, LatLngTuple, Layer, MapOptions, polyline, Rectangle, rectangle, tileLayer } from 'leaflet';
import { FormsModule } from '@angular/forms';
import { DecimalPipe } from '@angular/common';
import { Adresse } from './data/adresse';
import { OptimizationResult } from './services/OptimizationResult';
import { ApiService } from './services/api.service';
import { Carto } from './services/carto';
import { Sweep } from './services/optimisation-sweep.service';
import { getMarker } from './utils/marker';
import { MatriceFacade } from './facades/matrice.facade';
import { SolutionFacade } from './facades/solution.facade';
import { OptimisationFacade } from './facades/optimisation.facade';

@Component({
  selector: 'app-root',
  imports: [FormsModule, LeafletModule, DecimalPipe],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App implements OnInit {
  private readonly _carto = inject(Carto);
  private readonly _sweep = inject(Sweep);
  private readonly _api = inject(ApiService);
  private readonly _injector = inject(Injector);

  // Facades
  protected readonly matriceFacade = inject(MatriceFacade);
  protected readonly solutionFacade = inject(SolutionFacade);
  protected readonly optimisationFacade = inject(OptimisationFacade);

  // Raccourcis vers les signals des facades (pour le template)
  protected get solutionEquitable() { return this.solutionFacade.solutionEquitable; }
  protected get solutionSweep() { return this.solutionFacade.solutionSweep; }
  protected get solutionCluster() { return this.solutionFacade.solutionCluster; }
  protected get solutionModal() { return this.solutionFacade.solutionModal; }
  protected get modalOuvert() { return this.solutionFacade.modalOuvert; }
  protected get isLoading() { return this._isLoading; }
  protected get loadingMessage() { return this._loadingMessage; }

  // Etat local
  protected nombreEquipesMax = signal<number>(1);
  protected heuresMaxParEquipe = signal<number>(10000);
  private equipes = signal<any[]>([]);
  private readonly _isLoading = signal<boolean>(false);
  private readonly _loadingMessage = signal<string>('');

  private readonly bounds = signal<LatLngBoundsLiteral>([[45.1, 5.6], [45.3, 5.9]]);
  protected readonly options: MapOptions = {
    zoom: 11,
    center: latLng(45.188529, 5.724524),
  };
  protected readonly optionsMini: MapOptions = {
    zoom: 11,
    center: latLng(45.188529, 5.724524),
    zoomControl: false, dragging: false,
    scrollWheelZoom: false, doubleClickZoom: false,
    touchZoom: false, keyboard: false
  };

  private readonly _adresses = signal<readonly Adresse[]>([]);
  private readonly _routes = signal<ReadonlyArray<ReadonlyArray<LatLngTuple>>>([]);
  private readonly _optimizationResult = signal<undefined | OptimizationResult>(undefined);
  protected readonly layers: Signal<Layer[]>;

  private readonly colors = [
    '#FF0000', '#00FF00', '#0000FF', '#FFFF00', '#FF00FF',
    '#00FFFF', '#FF8800', '#8800FF', '#00FF88', '#FF0088',
    '#88FF00', '#0088FF', '#FF4400', '#4400FF', '#00FF44',
    '#FF0044', '#44FF00', '#0044FF', '#FFAA00', '#AA00FF',
    '#00FFAA', '#FF00AA', '#AAFF00', '#00AAFF', '#FF2200',
    '#2200FF', '#00FF22', '#FF0022', '#22FF00', '#0022FF'
  ];

  constructor() {
    const back = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18, attribution: '...'
    });
    const bboxRectangle: Signal<Rectangle> = computed<Rectangle>(
        () => rectangle(this.bounds(), { color: 'blue', weight: 1 })
    );
    this.layers = computed<Layer[]>(() => [
      back,
      bboxRectangle(),
      ...this._adresses().map((a, i) =>
          getMarker(a, i === this._adresses().length - 1 ? 'black' : 'blue')
      ),
      ...this._routes().map((r, i) => polyline([...r], {
        color: this.colors[i % this.colors.length],
        weight: 4, opacity: 0.8, smoothFactor: 1
      }))
    ]);
  }

  // Délégations vers les facades pour le template
  protected calculerCout(d: number) { return this.solutionFacade.calculerCout(d); }
  protected meilleureSolution() { return this.solutionFacade.meilleureSolution(); }
  protected ouvrirModal(s: any) { this.solutionFacade.ouvrirModal(s); }
  protected fermerModal() { this.solutionFacade.fermerModal(); }

  protected async validerSolution(id: number): Promise<void> {
    this._isLoading.set(true);
    this._loadingMessage.set('Validation...');
    try {
      await this.solutionFacade.valider(id);
    } finally {
      this._isLoading.set(false);
      this._loadingMessage.set('');
    }
  }

  protected async afficherSolutionSurCarte(solution: any): Promise<void> {
    this._isLoading.set(true);
    this._loadingMessage.set('Chargement des routes...');
    try {
      const parking = this._adresses().at(-1)!;
      const routes = await this.optimisationFacade.afficherDepuisBackend(solution, parking);
      this._routes.set(routes);
      this.solutionFacade.fermerModal();
    } finally {
      this._isLoading.set(false);
      this._loadingMessage.set('');
    }
  }

  // =============================================
  // INITIALISATION
  // =============================================
  ngOnInit(): void {
    this._isLoading.set(true);
    this._loadingMessage.set('Chargement des équipes...');

    this._api.getAllEquipes().subscribe({
      next: async (equipes) => {
        this.equipes.set(equipes);
        this.nombreEquipesMax.set(equipes.length);
        this.heuresMaxParEquipe.set(Math.max(...equipes.map((e: any) => e.nbHeuresMax)));

        this._loadingMessage.set('Chargement des commandes...');
        this._api.getAdressesCommandes().subscribe({
          next: async (adresses) => {
            this._adresses.set(adresses);
            this._loadingMessage.set('Vérification du cache matrice...');
            try {
              await this.matriceFacade.chargerMatrice(adresses);
            } catch (err) {
              console.error('Erreur matrice:', err);
            } finally {
              this._isLoading.set(false);
              this._loadingMessage.set('');
            }
          },
          error: () => this._isLoading.set(false)
        });
      },
      error: () => this._isLoading.set(false)
    });
  }

  // =============================================
  // OPTIMISATION EQUITABLE
  // =============================================
  protected async optimizeRoutesEquitable(nbVehicules: number, maxTime: number): Promise<void> {
    setTimeout(() => this._isLoading.set(true), 0);
    const adresses = this._adresses();
    if (adresses.length === 0) return;

    try {
      this._isLoading.set(true);
      this._loadingMessage.set('Optimisation équitable en cours...');
      this._routes.set([]);

      const { results, routes } = await this.optimisationFacade.optimiserEquitable(
          adresses, nbVehicules, maxTime
      );
      this._routes.set(routes);

      this._loadingMessage.set('Sauvegarde solution équitable...');
      const solution = await this.solutionFacade.sauvegarder(
          results, adresses.slice(0, -1), 'EQUITABLE', this.equipes()
      );
      this.solutionFacade.solutionEquitable.set(solution);

    } catch (err) {
      console.error('Erreur optimisation équitable:', err);
    } finally {
      this._isLoading.set(false);
      this._loadingMessage.set('');
    }
  }

  // =============================================
  // OPTIMISATION CLUSTER
  // =============================================
  protected async optimizeRoutesCluster(nbVehicules: number, maxTime: number): Promise<void> {
    setTimeout(() => this._isLoading.set(true), 0);
    const adresses = this._adresses();
    if (adresses.length === 0) return;

    try {
      this._isLoading.set(true);
      this._loadingMessage.set('Optimisation clusters en cours...');
      this._routes.set([]);

      const { results, routes } = await this.optimisationFacade.optimiserCluster(
          adresses, nbVehicules, maxTime, this.matriceFacade.matrice()
      );
      this._routes.set(routes);

      this._loadingMessage.set('Sauvegarde solution clusters...');
      const solution = await this.solutionFacade.sauvegarder(
          results, adresses.slice(0, -1), 'CLUSTER', this.equipes()
      );
      this.solutionFacade.solutionCluster.set(solution);

    } catch (err) {
      console.error('Erreur optimisation cluster:', err);
    } finally {
      this._isLoading.set(false);
      this._loadingMessage.set('');
    }
  }

  // =============================================
  // OPTIMISATION SWEEP
  // =============================================
  protected async optimizationSweeper(nbVehicules: number, maxTime: number): Promise<void> {
    setTimeout(() => this._isLoading.set(true), 0);
    const adresses = this._adresses();
    if (adresses.length === 0) return;

    try {
      this._isLoading.set(true);
      this._loadingMessage.set('Optimisation balayeuse en cours...');
      let vehiculesRestant = nbVehicules;
      this._routes.set([]);

      const parking = adresses.at(-1)!;
      const angles = this._sweep.constructionDesAngles(adresses, parking);
      const chunks = this._sweep.constructionChunkes(angles);

      for (const chunk of chunks) {
        let routesAvant = JSON.parse(JSON.stringify(this._routes()));
        let chunkSolved = false;
        if (vehiculesRestant === 0) break;

        const chunkWithParking = [...chunk, parking];
        for (let v = 1; v <= 3; v++) {
          if (v > vehiculesRestant) break;
          await this.optimizeRoutesAndAppend(v, maxTime, chunkWithParking);
          const unassigned = this._optimizationResult()?.unassigned?.length ?? 0;
          if (unassigned === 0) {
            vehiculesRestant -= v;
            chunkSolved = true;
            break;
          } else {
            this._routes.set(routesAvant);
            this._optimizationResult.set(undefined);
          }
        }
        if (!chunkSolved) this._routes.set(routesAvant);
        await new Promise(r => setTimeout(r, 1000));
      }

      const lastResult = this._optimizationResult();
      if (lastResult) {
        this._loadingMessage.set('Sauvegarde solution balayeuse...');
        const solution = await this.solutionFacade.sauvegarder(
            [lastResult], adresses.slice(0, -1), 'SWEEP', this.equipes()
        );
        this.solutionFacade.solutionSweep.set(solution);
      }

    } catch (err) {
      console.error('Erreur optimisation sweep:', err);
    } finally {
      this._isLoading.set(false);
      this._loadingMessage.set('');
    }
  }

  // =============================================
  // UTILITAIRES SWEEP
  // =============================================
  private optimizeRoutesSweepe(nbVehicules: number, maxTime: number, adresses?: readonly Adresse[]): void {
    if (!adresses || adresses.length === 0) return;
    this._carto.optimize({
      nbVehicules, maxTimePerVehicule: maxTime,
      adresses: adresses.slice(0, -1),
      parking: adresses.at(-1)!
    }).catch(err => {
      console.error('Erreur:', err);
      this._optimizationResult.set(undefined);
      return undefined;
    }).then(opt => {
      this._optimizationResult.set(opt);
      if (!opt) return undefined;
      return Promise.all(opt.routes.map(r =>
          this._carto.getDirections(r.steps.map(s => s.location))
      ));
    }).then(routes => this._routes.set(routes ?? []));
  }

  protected async optimizeRoutesAndAppend(nbVehicules: number, maxTime: number, adresses: readonly Adresse[]): Promise<void> {
    const previousRoutes = this._routes();
    this.optimizeRoutesSweepe(nbVehicules, maxTime, adresses);

    const newRoutes = await runInInjectionContext(
        this._injector,
        () => new Promise<ReadonlyArray<ReadonlyArray<LatLngTuple>>>(resolve => {
          const ref = effect(() => {
            const current = this._routes();
            if (current !== previousRoutes) {
              ref.destroy();
              resolve(current);
            }
          });
        })
    );
    this._routes.set([...previousRoutes, ...newRoutes]);
  }
}