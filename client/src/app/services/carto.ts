import { inject, Injectable } from '@angular/core';
import { Adresse } from '../data/adresse';
import { LatLngTuple } from 'leaflet';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { orsKey } from './orsKey';
import { OptimizationResult, parseOptimizationResultP, RouteStepBase } from './OptimizationResult';
import { GeoJSONFeatureCollectionSchema, GeoJSONLineStringSchema } from 'zod-geojson';

@Injectable({
  providedIn: 'root',
})
export class Carto {
  private readonly _httpClient = inject(HttpClient);

  private sleep(ms: number): Promise<void> {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  public async optimize(params: Readonly<{
    nbVehicules: number,
    maxTimePerVehicule: number,
    adresses: readonly Adresse[],
    parking: Adresse
  }>): Promise<OptimizationResult> {

    await this.sleep(3000);

    const { nbVehicules, maxTimePerVehicule, adresses, parking } = params;
    const parkingLngLat: [number, number] = [parking.lng, parking.lat];

    const LVehicules = Array.from(
      { length: nbVehicules },
      (_, i) => ({
        id: i + 1,
        profile: "driving-car",
        start: parkingLngLat,
        end: parkingLngLat,
        max_travel_time: maxTimePerVehicule,
      })
    );

    const Ljobs = adresses.map((a, i) => ({
      id: i,
      location: [a.lng, a.lat],
      setup: 30,
      service: 300,
    }));

    const req$ = this._httpClient.post(
      'https://api.openrouteservice.org/optimization',
      {
        jobs: Ljobs,
        vehicles: LVehicules,
        options: { g: true },
      },
      {
        headers: { Authorization: orsKey }
      }
    );

    return firstValueFrom(req$).then(parseOptimizationResultP);
  }

  public async getDirections(lngLatCoordinates: readonly RouteStepBase['location'][]): Promise<ReadonlyArray<LatLngTuple>> {

    await this.sleep(3000);

    const req$ = this._httpClient.post(
      'https://api.openrouteservice.org/v2/directions/driving-car/geojson',
      { coordinates: lngLatCoordinates },
      {
        headers: { Authorization: orsKey }
      }
    );

    return firstValueFrom(req$).then(
      res => GeoJSONFeatureCollectionSchema.parseAsync(res)
    ).then(
      fc => Promise.all(fc.features.map(f => GeoJSONLineStringSchema.parseAsync(f.geometry)))
    ).then(
      Lgeojson => Lgeojson.flatMap(geojson => geojson.coordinates.map(geoJsonLngLatToLatLng))
    );
  }

  public async getDistanceMatrix(adresses: readonly Adresse[]): Promise<{
    distances: number[][];
    durations: number[][];
    sources: Array<{ location: [number, number]; snapped_distance: number }>;
    destinations: Array<{ location: [number, number]; snapped_distance: number }>;
    metadata?: any;
  }> {
    const maxLocationsPerRequest = 50;
    const totalAddresses = adresses.length;

    if (totalAddresses <= maxLocationsPerRequest) {
      const locations = adresses.map(a => [a.lng, a.lat]);
      const req$ = this._httpClient.post(
        'https://api.openrouteservice.org/v2/matrix/driving-car',
        { locations, metrics: ['distance', 'duration'] },
        { headers: { Authorization: orsKey } }
      );

      const result = await firstValueFrom(req$) as any;
      return {
        distances: result.distances || [],
        durations: result.durations || [],
        sources: result.sources || [],
        destinations: result.destinations || [],
        metadata: result.metadata
      };
    }

    const fullDistanceMatrix: number[][] = Array(totalAddresses).fill(null).map(() => Array(totalAddresses).fill(0));
    const fullDurationMatrix: number[][] = Array(totalAddresses).fill(null).map(() => Array(totalAddresses).fill(0));
    const allSources: Array<{ location: [number, number]; snapped_distance: number }> = Array(totalAddresses).fill(null).map(() => ({ location: [0, 0] as [number, number], snapped_distance: 0 }));
    const allDestinations: Array<{ location: [number, number]; snapped_distance: number }> = Array(totalAddresses).fill(null).map(() => ({ location: [0, 0] as [number, number], snapped_distance: 0 }));

    const numChunks = Math.ceil(totalAddresses / maxLocationsPerRequest);
    let requestCount = 0;
    const totalRequests = numChunks * numChunks;
    let lastMetadata: any = null;

    for (let i = 0; i < numChunks; i++) {
      for (let j = 0; j < numChunks; j++) {
        requestCount++;
        const startI = i * maxLocationsPerRequest;
        const endI = Math.min((i + 1) * maxLocationsPerRequest, totalAddresses);
        const startJ = j * maxLocationsPerRequest;
        const endJ = Math.min((j + 1) * maxLocationsPerRequest, totalAddresses);

        const sourceAddresses = adresses.slice(startI, endI);
        const destAddresses = adresses.slice(startJ, endJ);

        const allLocations = [
          ...sourceAddresses.map(a => [a.lng, a.lat]),
          ...destAddresses.map(a => [a.lng, a.lat])
        ];

        const sources = Array.from({ length: sourceAddresses.length }, (_, idx) => idx);
        const destinations = Array.from({ length: destAddresses.length }, (_, idx) => idx + sourceAddresses.length);

        const req$ = this._httpClient.post(
          'https://api.openrouteservice.org/v2/matrix/driving-car',
          { locations: allLocations, sources, destinations, metrics: ['distance', 'duration'] },
          { headers: { Authorization: orsKey } }
        );

        try {
          const result = await firstValueFrom(req$) as any;
          if (result.metadata) lastMetadata = result.metadata;

          for (let localI = 0; localI < result.distances.length; localI++) {
            for (let localJ = 0; localJ < result.distances[localI].length; localJ++) {
              fullDistanceMatrix[startI + localI][startJ + localJ] = result.distances[localI][localJ];
              fullDurationMatrix[startI + localI][startJ + localJ] = result.durations[localI][localJ];
            }
          }

          for (let localI = 0; localI < result.sources.length; localI++) allSources[startI + localI] = result.sources[localI];
          for (let localJ = 0; localJ < result.destinations.length; localJ++) allDestinations[startJ + localJ] = result.destinations[localJ];

          if (requestCount < totalRequests) await this.sleep(3000);

        } catch (error) {
          console.error(`Erreur requete ${requestCount}:`, error);
          throw error;
        }
      }
    }

    return { distances: fullDistanceMatrix, durations: fullDurationMatrix, sources: allSources, destinations: allDestinations, metadata: lastMetadata };
  }
}

export function geoJsonLngLatToLatLng(lngLat: number[]): LatLngTuple {
  return [lngLat[1], lngLat[0]];
}