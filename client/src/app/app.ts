import { Component, computed, effect, inject, OnInit, Signal, signal, Injector, runInInjectionContext } from '@angular/core';
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
import { firstValueFrom } from 'rxjs';

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

  protected readonly matriceFacade = inject(MatriceFacade);
  protected readonly solutionFacade = inject(SolutionFacade);
  protected readonly optimisationFacade = inject(OptimisationFacade);

  protected get solutionEquitable() { return this.solutionFacade.solutionEquitable; }
  protected get solutionSweep() { return this.solutionFacade.solutionSweep; }
  protected get solutionCluster() { return this.solutionFacade.solutionCluster; }
  protected get solutionModal() { return this.solutionFacade.solutionModal; }
  protected get modalOuvert() { return this.solutionFacade.modalOuvert; }
  protected get isLoading() { return this._isLoading; }
  protected get loadingMessage() { return this._loadingMessage; }

  protected nombreEquipesMax = signal<number>(1);
  protected heuresMaxParEquipe = signal<number>(10000);
  protected equipes = signal<any[]>([]);
  protected readonly commandes = signal<any[]>([]);
  private readonly _isLoading = signal<boolean>(false);
  private readonly _loadingMessage = signal<string>('');
  protected readonly _erreur = signal<string>('');
  protected readonly _warning = signal<string>('');

  private readonly _maxTimeParAlgo = signal<{
    EQUITABLE?: number,
    SWEEP?: number,
    CLUSTER?: number
  }>({});

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
  private readonly _sweepResults = signal<{result: OptimizationResult, adresses: readonly Adresse[]}[]>([]);

  private readonly _routesParAlgo = signal<{
    EQUITABLE?: ReadonlyArray<ReadonlyArray<LatLngTuple>>,
    SWEEP?: ReadonlyArray<ReadonlyArray<LatLngTuple>>,
    CLUSTER?: ReadonlyArray<ReadonlyArray<LatLngTuple>>
  }>({});

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

  protected calculerCout(d: number) { return this.solutionFacade.calculerCout(d); }
  protected meilleureSolution() { return this.solutionFacade.meilleureSolution(); }

  protected ouvrirModal(s: any): void {
    this.solutionFacade.ouvrirModal(s);
    const algo = s.nomAlgorithme as 'EQUITABLE' | 'SWEEP' | 'CLUSTER';
    const routesCachees = this._routesParAlgo()[algo];
    if (routesCachees) this._routes.set(routesCachees);
  }

  protected fermerModal() {
    this.solutionFacade.fermerModal();
    this._warning.set('');
  }

  protected async chargerCommandes(): Promise<void> {
    this._isLoading.set(true);
    this._loadingMessage.set('Chargement des commandes...');
    this._api.getCommandesNonLivrees().subscribe({
      next: async (cmds) => {
        this.commandes.set(cmds);
        const adressesCommandes = cmds
            .map((cmd: any) => ({
              name: cmd.numeroCommande,
              lat: cmd.latitude,
              lng: cmd.longtitude,
              numeroCommande: cmd.numeroCommande,
            }))
            .filter((a: any) => a.lat != null && a.lng != null);
        this._loadingMessage.set('Chargement de l\'entrepôt...');
        this._api.getEntrepot(1).subscribe({
          next: async (entrepot) => {
            const parking: any = {
              name: entrepot.nom,
              lat: entrepot.latitude,
              lng: entrepot.longtitude,
            };
            const adresses = [...adressesCommandes, parking];
            this._adresses.set(adresses);
            this._loadingMessage.set('Calcul de la matrice de distances...');
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

  protected async validerSolution(id: number): Promise<void> {
    this._isLoading.set(true);
    this._loadingMessage.set('Validation...');
    try {
      await this.solutionFacade.valider(id);
      await firstValueFrom(this._api.planifierCommandes(id));

      // Met à jour le statut des commandes en front
      const solution =
          this.solutionFacade.solutionEquitable()?.id === id ? this.solutionFacade.solutionEquitable() :
              this.solutionFacade.solutionSweep()?.id === id ? this.solutionFacade.solutionSweep() :
                  this.solutionFacade.solutionCluster();

      if (solution) {
        const commandesPlanifiees = new Set(
            solution.tournees.flatMap((t: any) => t.commandes.map((c: any) => c.numeroCommande))
        );
        this.commandes.update(cmds =>
            cmds.map((cmd: any) => commandesPlanifiees.has(cmd.numeroCommande)
                ? { ...cmd, status: 'PLANIFIEE' }
                : cmd
            )
        );
      }
    } finally {
      this._isLoading.set(false);
      this._loadingMessage.set('');
    }
  }

  protected async afficherSolutionSurCarte(solution: any): Promise<void> {
    const algo = solution.nomAlgorithme as 'EQUITABLE' | 'SWEEP' | 'CLUSTER';
    const routesCachees = this._routesParAlgo()[algo];
    if (routesCachees) this._routes.set(routesCachees);
    this.solutionFacade.fermerModal();
  }

  ngOnInit(): void {
    this._isLoading.set(true);
    this._loadingMessage.set('Chargement des équipes...');
    this._api.getAllEquipes().subscribe({
      next: (equipes) => {
        this.equipes.set(equipes);
        this.nombreEquipesMax.set(equipes.length);
        this.heuresMaxParEquipe.set(Math.max(...equipes.map((e: any) => e.nbHeuresMax)));
        this._isLoading.set(false);
        this._loadingMessage.set('');
      },
      error: () => this._isLoading.set(false)
    });
  }

  protected async supprimerTournee(tourneeId: number): Promise<void> {
    const solution = this._solutionEnEdition();
    if (!solution) return;

    try {
      // Supprimer en base
      await firstValueFrom(this._api.deleteTournee(tourneeId));

      // Supprimer localement
      solution.tournees = solution.tournees.filter(
          (t: any) => t.idTournee !== tourneeId
      );

      const nbCommandesLivrees = solution.tournees.reduce(
          (sum: number, t: any) => sum + (t.commandes?.length ?? 0), 0
      );

      this._solutionEnEdition.set({ ...solution, nbCommandesLivrees });

      // Recalculer les routes sans la tournée supprimée
      const algo = solution.nomAlgorithme as 'EQUITABLE' | 'SWEEP' | 'CLUSTER';
      const parking = this._adresses().at(-1)!;

      const nouvellesRoutes = await this.optimisationFacade.afficherDepuisBackend(
          { ...solution, nbCommandesLivrees },
          parking
      );

      // Mettre à jour les routes sur la carte
      this._routes.set(nouvellesRoutes);
      this._routesParAlgo.update(r => ({ ...r, [algo]: nouvellesRoutes }));

    } catch (err) {
      console.error('Erreur suppression tournée:', err);
      this._erreur.set('Erreur lors de la suppression de la tournée');
      setTimeout(() => this._erreur.set(''), 3000);
    }
  }

  protected async optimizeRoutesEquitable(nbVehicules: number, maxTime: number): Promise<void> {
    if (nbVehicules > this.nombreEquipesMax()) {
      this._erreur.set(`⚠️ Seulement ${this.nombreEquipesMax()} équipes disponibles.`);
      setTimeout(() => this._erreur.set(''), 3000);
      return;
    }
    if (this._adresses().length === 0) {
      this._erreur.set('⚠️ Chargez les commandes avant de lancer l\'optimisation.');
      setTimeout(() => this._erreur.set(''), 3000);
      return;
    }
    const adresses = this._adresses();
    if (adresses.length === 0) return;
    try {
      this._isLoading.set(true);
      this._loadingMessage.set('Optimisation équitable en cours...');
      this._routes.set([]);
      const { results, tranches, routes } = await this.optimisationFacade.optimiserEquitable(
          adresses, nbVehicules, maxTime
      );
      this._routes.set(routes);
      this._routesParAlgo.update(r => ({ ...r, EQUITABLE: routes }));
      this._maxTimeParAlgo.update(m => ({ ...m, EQUITABLE: maxTime }));
      this._loadingMessage.set('Sauvegarde solution équitable...');
      const solution = await this.solutionFacade.sauvegarderEquitable(
          results, tranches, this.equipes()
      );
      this.solutionFacade.solutionEquitable.set(solution);
    } catch (err) {
      console.error('Erreur optimisation équitable:', err);
    } finally {
      this._isLoading.set(false);
      this._loadingMessage.set('');
    }
  }

  protected async optimizeRoutesCluster(nbVehicules: number, maxTime: number): Promise<void> {
    if (nbVehicules > this.nombreEquipesMax()) {
      this._erreur.set(`⚠️ Seulement ${this.nombreEquipesMax()} équipes disponibles.`);
      setTimeout(() => this._erreur.set(''), 3000);
      return;
    }
    if (this._adresses().length === 0) {
      this._erreur.set('⚠️ Chargez les commandes avant de lancer l\'optimisation.');
      setTimeout(() => this._erreur.set(''), 3000);
      return;
    }
    const adresses = this._adresses();
    if (adresses.length === 0) return;
    try {
      this._isLoading.set(true);
      this._loadingMessage.set('Optimisation clusters en cours...');
      this._routes.set([]);
      const { results, tranches, routes } = await this.optimisationFacade.optimiserCluster(
          adresses, nbVehicules, maxTime, this.matriceFacade.matrice()
      );
      this._routes.set(routes);
      this._routesParAlgo.update(r => ({ ...r, CLUSTER: routes }));
      this._maxTimeParAlgo.update(m => ({ ...m, CLUSTER: maxTime }));
      this._loadingMessage.set('Sauvegarde solution clusters...');
      const solution = await this.solutionFacade.sauvegarderCluster(
          results, tranches, this.equipes()
      );
      this.solutionFacade.solutionCluster.set(solution);
    } catch (err) {
      console.error('Erreur optimisation cluster:', err);
    } finally {
      this._isLoading.set(false);
      this._loadingMessage.set('');
    }
  }

  protected async optimizationSweeper(nbVehicules: number, maxTime: number): Promise<void> {
    if (nbVehicules > this.nombreEquipesMax()) {
      this._erreur.set(`⚠️ Seulement ${this.nombreEquipesMax()} équipes disponibles.`);
      setTimeout(() => this._erreur.set(''), 3000);
      return;
    }
    if (this._adresses().length === 0) {
      this._erreur.set('⚠️ Chargez les commandes avant de lancer l\'optimisation.');
      setTimeout(() => this._erreur.set(''), 3000);
      return;
    }
    const adresses = this._adresses();
    if (adresses.length === 0) return;
    try {
      this._isLoading.set(true);
      this._loadingMessage.set('Optimisation balayeuse en cours...');
      let vehiculesRestant = nbVehicules;
      this._routes.set([]);
      this._sweepResults.set([]);
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
            const currentResult = this._optimizationResult();
            if (currentResult) {
              this._sweepResults.update(prev => [...prev, { result: currentResult, adresses: chunk }]);
            }
            break;
          } else {
            this._routes.set(routesAvant);
            this._optimizationResult.set(undefined);
          }
        }
        if (!chunkSolved) this._routes.set(routesAvant);
        await new Promise(r => setTimeout(r, 1000));
      }
      const allResults = this._sweepResults();
      if (allResults.length > 0) {
        this._routesParAlgo.update(r => ({ ...r, SWEEP: this._routes() }));
        this._maxTimeParAlgo.update(m => ({ ...m, SWEEP: maxTime }));
        this._loadingMessage.set('Sauvegarde solution balayeuse...');
        const solution = await this.solutionFacade.sauvegarderSweep(allResults, 'SWEEP', this.equipes());
        this.solutionFacade.solutionSweep.set(solution);
      }
    } catch (err) {
      console.error('Erreur optimisation sweep:', err);
    } finally {
      this._isLoading.set(false);
      this._loadingMessage.set('');
    }
  }

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
      return Promise.all(opt.routes.map(r => this._carto.getDirections(r.steps.map(s => s.location))));
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
            if (current !== previousRoutes) { ref.destroy(); resolve(current); }
          });
        })
    );
    this._routes.set([...previousRoutes, ...newRoutes]);
  }

  protected layersModal(): Layer[] {
    const back = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', { maxZoom: 18, attribution: '...' });
    const bboxRectangle: Rectangle = rectangle(this.bounds(), { color: 'blue', weight: 1 });
    const markers = this._adresses().map((a, i) => getMarker(a, i === this._adresses().length - 1 ? 'black' : 'blue'));
    const polylines = this._routes().map((r, i) => polyline([...r], { color: this.colors[i % this.colors.length], weight: 4, opacity: 0.8, smoothFactor: 1 }));
    return [back, bboxRectangle, ...markers, ...polylines];
  }

  protected readonly layersEquitable = computed<Layer[]>(() => {
    const back = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', { maxZoom: 18, attribution: '...' });
    const routes = this._routesParAlgo().EQUITABLE ?? [];
    return [back, ...routes.map((r, i) => polyline([...r], { color: this.colors[i % this.colors.length], weight: 3, opacity: 0.8 }))];
  });

  protected readonly layersSweep = computed<Layer[]>(() => {
    const back = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', { maxZoom: 18, attribution: '...' });
    const routes = this._routesParAlgo().SWEEP ?? [];
    return [back, ...routes.map((r, i) => polyline([...r], { color: this.colors[i % this.colors.length], weight: 3, opacity: 0.8 }))];
  });

  protected readonly layersCluster = computed<Layer[]>(() => {
    const back = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', { maxZoom: 18, attribution: '...' });
    const routes = this._routesParAlgo().CLUSTER ?? [];
    return [back, ...routes.map((r, i) => polyline([...r], { color: this.colors[i % this.colors.length], weight: 3, opacity: 0.8 }))];
  });

  protected readonly tourneeSelectionnee = signal<any | null>(null);

  protected ouvrirDetailTournee(tournee: any): void {
    const equipe = this.equipes().find(e => e.numeroEquipe === tournee.numeroEquipe);
    this.tourneeSelectionnee.set({ ...tournee, equipeDetail: equipe });
  }

  protected fermerDetailTournee(): void {
    this.tourneeSelectionnee.set(null);
  }

  protected readonly _solutionEnEdition = signal<any | null>(null);
  protected readonly _modificationEnCours = signal<boolean>(false);

  protected activerEdition(solution: any): void {
    this._solutionEnEdition.set(JSON.parse(JSON.stringify(solution)));
    this._modificationEnCours.set(true);
    this._warning.set('');
  }

  protected annulerEdition(): void {
    this._solutionEnEdition.set(null);
    this._modificationEnCours.set(false);
    this._erreur.set('');
    this._warning.set('');
  }

  protected deplacerCommande(commande: any, tourneeSourceId: number, tourneeCibleId: number): void {
    const solution = this._solutionEnEdition();
    if (!solution) return;
    const tourneeSource = solution.tournees.find((t: any) => t.idTournee === tourneeSourceId);
    const tourneeCible = solution.tournees.find((t: any) => t.idTournee === tourneeCibleId);
    if (!tourneeSource || !tourneeCible) return;
    tourneeSource.commandes = tourneeSource.commandes.filter(
        (c: any) => c.numeroCommande !== commande.numeroCommande
    );
    tourneeCible.commandes.push(commande);
    const nbCommandesLivrees = solution.tournees.reduce(
        (sum: number, t: any) => sum + (t.commandes?.length ?? 0), 0
    );
    this._solutionEnEdition.set({ ...solution, nbCommandesLivrees });
  }

  protected getEquipeByNumero(numeroEquipe: any): any {
    return this.equipes().find(e => e.numeroEquipe === +numeroEquipe);
  }

  protected changerEquipe(tourneeId: number, nouvelleEquipe: any): void {
    const solution = this._solutionEnEdition();
    if (!solution) return;
    const equipeDejaUtilisee = solution.tournees.find(
        (t: any) => t.idTournee !== tourneeId && t.numeroEquipe === nouvelleEquipe.numeroEquipe
    );
    if (equipeDejaUtilisee) {
      this._erreur.set(`⚠️ L'équipe ${nouvelleEquipe.numeroEquipe} est déjà assignée à la tournée #${equipeDejaUtilisee.idTournee}`);
      return;
    }
    this._erreur.set('');
    const tournee = solution.tournees.find((t: any) => t.idTournee === tourneeId);
    if (!tournee) return;
    tournee.numeroEquipe = nouvelleEquipe.numeroEquipe;
    this._solutionEnEdition.set({ ...solution });
  }

  // Supprimer une commande d'une tournée
  protected supprimerCommande(commande: any, tourneeId: number): void {
    const solution = this._solutionEnEdition();
    if (!solution) return;

    const tournee = solution.tournees.find((t: any) => t.idTournee === tourneeId);
    if (!tournee) return;

    tournee.commandes = tournee.commandes.filter(
        (c: any) => c.numeroCommande !== commande.numeroCommande
    );

    const nbCommandesLivrees = solution.tournees.reduce(
        (sum: number, t: any) => sum + (t.commandes?.length ?? 0), 0
    );
    this._solutionEnEdition.set({ ...solution, nbCommandesLivrees });
  }

// Récupérer les commandes disponibles (non présentes dans aucune tournée de la solution)
  protected commandesDisponibles(): any[] {
    const solution = this._solutionEnEdition();
    if (!solution) return [];

    // Toutes les commandes déjà dans la solution
    const commandesDansSolution = new Set<string>(
        solution.tournees.flatMap((t: any) =>
            (t.commandes ?? []).map((c: any) => c.numeroCommande)
        )
    );

    // Commandes non livrées qui ne sont pas dans la solution
    return this.commandes().filter(
        (cmd: any) => !commandesDansSolution.has(cmd.numeroCommande)
    );
  }

// Ajouter une commande à une tournée
  protected ajouterCommande(commande: any, tourneeId: number): void {
    const solution = this._solutionEnEdition();
    if (!solution) return;

    const tournee = solution.tournees.find((t: any) => t.idTournee === tourneeId);
    if (!tournee) return;

    // Vérifier que la commande n'est pas déjà dans cette tournée
    const dejaPresente = tournee.commandes.some(
        (c: any) => c.numeroCommande === commande.numeroCommande
    );
    if (dejaPresente) return;

    // Ajouter avec les coordonnées depuis la liste des commandes chargées
    const commandeComplete = {
      numeroCommande: commande.numeroCommande,
      latitude: commande.latitude,
      longtitude: commande.longtitude
    };

    tournee.commandes.push(commandeComplete);

    const nbCommandesLivrees = solution.tournees.reduce(
        (sum: number, t: any) => sum + (t.commandes?.length ?? 0), 0
    );
    this._solutionEnEdition.set({ ...solution, nbCommandesLivrees });
  }

  protected readonly _sauvegardEnCours = signal<boolean>(false);
  protected readonly _sauvegardSucces = signal<boolean>(false);
  protected async sauvegarderModifications(): Promise<void> {
    const solutionModifiee = this._solutionEnEdition();
    const solutionOriginale = this.solutionFacade.solutionModal();
    this._sauvegardEnCours.set(true);
    this._sauvegardSucces.set(false);
    if (!solutionModifiee || !solutionOriginale) return;
    const equipesUtilisees = solutionModifiee.tournees.map((t: any) => t.numeroEquipe);
    const equipesUniques = new Set(equipesUtilisees);
    if (equipesUtilisees.length !== equipesUniques.size) {
      this._erreur.set('⚠️ Deux tournées ne peuvent pas avoir la même équipe.');
      return;
    }
    this._isLoading.set(true);
    this._loadingMessage.set('Recalcul des routes en cours...');
    const warnings: string[] = [];
    try {
      const parking = this._adresses().at(-1)!;
      const algo = solutionModifiee.nomAlgorithme as 'EQUITABLE' | 'SWEEP' | 'CLUSTER';
      const maxTimeOriginal = this._maxTimeParAlgo()[algo] ?? 34200;
      const tourneesCommandesModifiees = solutionModifiee.tournees.filter((tourneeModif: any) => {
        const tourneeOriginale = solutionOriginale.tournees.find(
            (t: any) => t.idTournee === tourneeModif.idTournee
        );
        if (!tourneeOriginale) return false;
        const commandesModif = tourneeModif.commandes.map((c: any) => c.numeroCommande).sort().join(',');
        const commandesOrig = tourneeOriginale.commandes.map((c: any) => c.numeroCommande).sort().join(',');
        return commandesModif !== commandesOrig;
      });
      const tourneesEquipeModifiee = solutionModifiee.tournees.filter((tourneeModif: any) => {
        const tourneeOriginale = solutionOriginale.tournees.find(
            (t: any) => t.idTournee === tourneeModif.idTournee
        );
        if (!tourneeOriginale) return false;
        const commandesModif = tourneeModif.commandes.map((c: any) => c.numeroCommande).sort().join(',');
        const commandesOrig = tourneeOriginale.commandes.map((c: any) => c.numeroCommande).sort().join(',');
        return commandesModif === commandesOrig && tourneeModif.numeroEquipe !== tourneeOriginale.numeroEquipe;
      });
      for (const tournee of tourneesCommandesModifiees) {
        const tourneeIndex = solutionModifiee.tournees.findIndex(
            (t: any) => t.idTournee === tournee.idTournee
        );
        if (!tournee.commandes || tournee.commandes.length === 0) {
          solutionModifiee.tournees[tourneeIndex]._jobsLivres = 0;
          solutionModifiee.tournees[tourneeIndex].tempsTotal = 0;
          solutionModifiee.tournees[tourneeIndex].distanceTotale = 0;
          await firstValueFrom(this._api.updateTournee({
            idTournee: tournee.idTournee,
            numeroEquipe: tournee.numeroEquipe,
            commandes_ids: [],
            tempsTotal: 0,
            distanceTotale: 0
          }));
          continue;
        }
        let optimResult: any = null;
        let maxTimeCourant = maxTimeOriginal;
        for (let tentative = 1; tentative <= 3; tentative++) {
          try {
            optimResult = await this._carto.optimize({
              nbVehicules: 1,
              maxTimePerVehicule: maxTimeCourant,
              adresses: tournee.commandes
                  .filter((cmd: any) => cmd.latitude && cmd.longtitude)
                  .map((cmd: any) => ({
                    name: cmd.numeroCommande,
                    lat: cmd.latitude,
                    lng: cmd.longtitude,
                    numeroCommande: cmd.numeroCommande
                  })),
              parking
            });
            const jobsLivres = optimResult.routes.reduce(
                (acc: number, r: any) => acc + r.steps.filter((s: any) => s.type === 'job').length, 0
            );
            solutionModifiee.tournees[tourneeIndex]._jobsLivres = jobsLivres;
            if (jobsLivres >= tournee.commandes.length) break;
            maxTimeCourant = maxTimeCourant * 2;
          } catch (err) {
            console.error(`Erreur optimize tentative ${tentative}:`, err);
          }
        }
        if (optimResult && optimResult.routes.length > 0) {
          const route = optimResult.routes[0];
          const jobsLivresFinal = optimResult.routes.reduce(
              (acc: number, r: any) => acc + r.steps.filter((s: any) => s.type === 'job').length, 0
          );
          solutionModifiee.tournees[tourneeIndex]._jobsLivres = jobsLivresFinal;
          solutionModifiee.tournees[tourneeIndex].tempsTotal = route.duration;
          solutionModifiee.tournees[tourneeIndex].distanceTotale = (route.distance ?? 0) / 1000;
          if (route.duration > maxTimeOriginal) {
            const heuresUtilisees = Math.round(route.duration / 3600 * 10) / 10;
            const heuresMax = Math.round(maxTimeOriginal / 3600 * 10) / 10;
            warnings.push(`⚠️ Tournée #${tournee.idTournee} : dépasse le temps max saisi (${heuresUtilisees}h > ${heuresMax}h)`);
          }
        }
        await firstValueFrom(this._api.updateTournee({
          idTournee: tournee.idTournee,
          numeroEquipe: tournee.numeroEquipe,
          commandes_ids: tournee.commandes.map((c: any) => c.numeroCommande),
          tempsTotal: solutionModifiee.tournees[tourneeIndex].tempsTotal,
          distanceTotale: solutionModifiee.tournees[tourneeIndex].distanceTotale
        }));
      }
      for (const tournee of tourneesEquipeModifiee) {
        await firstValueFrom(this._api.updateTournee({
          idTournee: tournee.idTournee,
          numeroEquipe: tournee.numeroEquipe,
          commandes_ids: tournee.commandes.map((c: any) => c.numeroCommande),
          tempsTotal: tournee.tempsTotal,
          distanceTotale: tournee.distanceTotale
        }));
      }
      let nbCommandesLivrees = 0;
      for (const tournee of solutionModifiee.tournees) {
        const estCommandesModifiee = tourneesCommandesModifiees.some(
            (t: any) => t.idTournee === tournee.idTournee
        );
        if (estCommandesModifiee) {
          nbCommandesLivrees += tournee._jobsLivres ?? tournee.commandes?.length ?? 0;
        } else {
          nbCommandesLivrees += tournee.commandes?.length ?? 0;
        }
      }
      const tempsTotal = solutionModifiee.tournees.reduce(
          (sum: number, t: any) => sum + (t.tempsTotal ?? 0), 0
      );
      const distanceTotale = solutionModifiee.tournees.reduce(
          (sum: number, t: any) => sum + (t.distanceTotale ?? 0), 0
      );
      const coutTotal = this.solutionFacade.calculerCout(distanceTotale);
      const solutionMiseAJour = {
        ...solutionModifiee,
        nbCommandesLivrees,
        tempsTotal,
        distanceTotale,
        coutTotal
      };
      this._loadingMessage.set('Sauvegarde en base...');
      await firstValueFrom(this._api.updateSolution(solutionMiseAJour.id, {
        tempsTotal,
        distanceTotale,
        nbCommandesLivrees,
        nomAlgorithme: algo
      }));
      if (algo === 'EQUITABLE') this.solutionFacade.solutionEquitable.set(solutionMiseAJour);
      if (algo === 'SWEEP') this.solutionFacade.solutionSweep.set(solutionMiseAJour);
      if (algo === 'CLUSTER') this.solutionFacade.solutionCluster.set(solutionMiseAJour);
      this._loadingMessage.set('Recalcul des routes sur la carte...');
      const nouvellesRoutes = await this.optimisationFacade.afficherDepuisBackend(
          solutionMiseAJour,
          parking
      );
      this._routesParAlgo.update(r => ({ ...r, [algo]: nouvellesRoutes }));
      this._routes.set(nouvellesRoutes);
      this.solutionFacade.fermerModal();
      setTimeout(() => {
        this.solutionFacade.ouvrirModal(solutionMiseAJour);
        this._routes.set(nouvellesRoutes);
        if (warnings.length > 0) {
          this._warning.set(warnings.join('\n'));
        }
      }, 50);
      this._sauvegardSucces.set(true);
      setTimeout(() => this._sauvegardSucces.set(false), 3000);
      this.annulerEdition();
    } catch (err) {
      console.error('Erreur sauvegarde modifications:', err);
    } finally {
      this._isLoading.set(false);
      this._loadingMessage.set('');
      this._sauvegardEnCours.set(false);
    }
  }
  protected readonly solutionValidee = signal<string>('');

  protected readonly _confirmationVisible = signal<boolean>(false);
  protected readonly _confirmationId = signal<number>(0);
  protected readonly _confirmationAlgo = signal<string>('');

  protected confirmerValidation(id: number, algo: string): void {
    this._confirmationId.set(id);
    this._confirmationAlgo.set(algo);
    this._confirmationVisible.set(true);
  }

  protected confirmerOui(): void {
    this.validerSolution(this._confirmationId());
    this.solutionValidee.set(this._confirmationAlgo());
    this._confirmationVisible.set(false);
  }

  protected confirmerNon(): void {
    this._confirmationVisible.set(false);
  }

  protected getSelectValue(event: Event): string {
    return (event.target as HTMLSelectElement).value;
  }
}