import { Adresse } from '../data/adresse';
import { OptimizationResult } from './OptimizationResult';

export async function optimiseEquitable(
  adresses: readonly Adresse[],
  nbVehiculesDemandes: number,
  maxTimePerVehicule: number,
  optimizeCallback: (params: {
    nbVehicules: number,
    maxTimePerVehicule: number,
    adresses: readonly Adresse[],
    parking: Adresse
  }) => Promise<OptimizationResult>
): Promise<{
  results: OptimizationResult[];
  tranches: Adresse[][];
  stats: {
    totalPoints: number;
    vehiculesDemandes: number;
    vehiculesMinimum: number;
    vehiculesUtilises: number;
    totalPaquets: number;
    totalDuree: number;
    totalCout: number;
    alerte?: string;
  }
}> {
  const parking = adresses[adresses.length - 1];
  const jobs = adresses.slice(0, -1);
  const totalPoints = jobs.length;

  const POINTS_MAX_PAR_PAQUET = 50;
  const vehiculesMinimum = Math.ceil(totalPoints / POINTS_MAX_PAR_PAQUET);

  if (nbVehiculesDemandes < vehiculesMinimum) {
    return {
      results: [],
      tranches: [],
      stats: {
        totalPoints,
        vehiculesDemandes: nbVehiculesDemandes,
        vehiculesMinimum,
        vehiculesUtilises: 0,
        totalPaquets: 0,
        totalDuree: 0,
        totalCout: 0,
        alerte: `${nbVehiculesDemandes}v < ${vehiculesMinimum}v minimum requis`
      }
    };
  }

  // Distribution équitable des points
  const pointsParVehicule = Math.floor(totalPoints / nbVehiculesDemandes);
  let restePoints = totalPoints - (pointsParVehicule * nbVehiculesDemandes);

  if (pointsParVehicule + 1 > POINTS_MAX_PAR_PAQUET) {
    return {
      results: [],
      tranches: [],
      stats: {
        totalPoints,
        vehiculesDemandes: nbVehiculesDemandes,
        vehiculesMinimum: 0,
        vehiculesUtilises: 0,
        totalPaquets: 0,
        totalDuree: 0,
        totalCout: 0,
        alerte: `${pointsParVehicule}pts/veh > ${POINTS_MAX_PAR_PAQUET}pts max`
      }
    };
  }

  // Tri par longitude pour des tournées géographiquement cohérentes
  const sorted = [...jobs].sort((a, b) => a.lng - b.lng);

  // Construction des tranches par véhicule
  const tranches: Adresse[][] = [];
  let indexPoint = 0;

  for (let i = 0; i < nbVehiculesDemandes; i++) {
    let pts = pointsParVehicule;
    if (restePoints > 0) {
      pts++;
      restePoints--;
    }
    tranches.push(sorted.slice(indexPoint, indexPoint + pts));
    indexPoint += pts;
  }

  console.log(`Équitable : ${nbVehiculesDemandes} véhicules, ${tranches.map(t => t.length).join('/')} points`);

  // 1 appel ORS par véhicule
  const results: OptimizationResult[] = [];
  let totalDuree = 0;
  let totalVehiculesUtilises = 0;
  let totalPointsLivres = 0;

  for (let i = 0; i < tranches.length; i++) {
    const tranche = tranches[i];
    if (tranche.length === 0) continue;

    console.log(`Véhicule ${i + 1}/${nbVehiculesDemandes} → ${tranche.length} points`);

    try {
      const result = await optimizeCallback({
        nbVehicules: 1,  // ← 1 véhicule par appel
        maxTimePerVehicule,
        adresses: tranche,
        parking
      });

      results.push(result);
      totalDuree += result.summary.duration;
      totalVehiculesUtilises += result.routes.length;

      const pointsLivres = result.routes.reduce((acc, route) =>
        acc + route.steps.filter(s => s.type === 'job').length, 0
      );
      totalPointsLivres += pointsLivres;

      console.log(`  ✓ ${pointsLivres}/${tranche.length} points livrés`);

    } catch (error) {
      console.error(`  ✗ Erreur véhicule ${i + 1}:`, error);
    }
  }

  const alerte = totalPointsLivres < totalPoints
    ? `${totalPoints - totalPointsLivres} points non livrés`
    : '';

  return {
    results,
    tranches,
    stats: {
      totalPoints,
      vehiculesDemandes: nbVehiculesDemandes,
      vehiculesMinimum,
      vehiculesUtilises: totalVehiculesUtilises,
      totalPaquets: tranches.length,
      totalDuree,
      totalCout: 0,
      alerte
    }
  };
}