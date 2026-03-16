import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';  // ← ajouter
import { Adresse } from '../data/adresse';  // ← ajouter

@Injectable({ providedIn: 'root' })
export class ApiService {

    private apiUrl = 'http://localhost:8080/api';

    constructor(private http: HttpClient) {}

    // ===== COMMANDES =====
    getCommandesNonLivrees(): Observable<any[]> {
        return this.http.get<any[]>(`${this.apiUrl}/commands/non-livres`);
    }

    getNombreCommandesNonLivrees(): Observable<number> {
        return this.http.get<number>(`${this.apiUrl}/commands/non-livres/count`);
    }

    getAdressesCommandes(): Observable<Adresse[]> {
        return this.http.get<any[]>(`${this.apiUrl}/commands/non-livres`).pipe(
            map(commandes => commandes.map(cmd => ({
                name: cmd.numeroCommande,
                lat: cmd.latitude,
                lng: cmd.longtitude  // ← correspond au champ dans le DTO
            })))
        );
    }

    // ===== EQUIPES =====
    getNombreEquipes(): Observable<number> {
        return this.http.get<number>(`${this.apiUrl}/equipes/nb-equipes`);
    }

    getHeuresMaxParEquipe(): Observable<any[]> {
        return this.http.get<any[]>(`${this.apiUrl}/equipes/heures-max`);
    }

    // ===== TOURNEES =====
    createTournee(tournee: any): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}/tournees/tournee`, tournee);
    }
    getTourneesByDate(date: string): Observable<any[]> {
        return this.http.get<any[]>(`${this.apiUrl}/tournees/${date}`);
    }
}