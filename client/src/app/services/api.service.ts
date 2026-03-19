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

    getAdressesCommandes(): Observable<Adresse[]> {
        return this.http.get<any[]>(`${this.apiUrl}/commands/non-livres`).pipe(
            map(commandes => commandes.map(cmd => ({
                name: cmd.numeroCommande,
                lat: cmd.latitude,
                lng: cmd.longtitude,
                numeroCommande: cmd.numeroCommande
            })))
        );
    }

    // ===== EQUIPES =====
    getNombreEquipes(): Observable<number> {
        return this.http.get<number>(`${this.apiUrl}/equipes/nb-equipes`);
    }
    getAllEquipes(): Observable<any[]> {
        return this.http.get<any[]>(`${this.apiUrl}/equipes`);
    }

    getHeuresMax(): Observable<number> {
        return this.http.get<number>(`${this.apiUrl}/equipes/heures-max`);
    }

    // ===== TOURNEES =====
    createTournee(tournee: any): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}/tournees/tournee`, tournee);
    }

    getTourneesByDate(date: string): Observable<any[]> {
        return this.http.get<any[]>(`${this.apiUrl}/tournees/${date}`);
    }

    // ===== SOLUTIONS =====
    saveSolution(solution: any): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}/solutions/solution`, solution);
    }

    getAllSolutions(): Observable<any[]> {
        return this.http.get<any[]>(`${this.apiUrl}/solutions`);
    }

    activerSolution(id: number): Observable<any> {
        return this.http.put<any>(`${this.apiUrl}/solutions/${id}/activer`, {});
    }
    getSolutionById(id: number): Observable<any> {
        return this.http.get<any>(`${this.apiUrl}/solutions/${id}`);
    }
    getEntrepot(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/entrepot/${id}`);
    }
   updateTournee(tournee: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/tournees/${tournee.idTournee}`, tournee);
    }
    updateSolution(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/solutions/${id}`, data);
    }

    deleteTournee(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/tournees/${id}`);
    }

    planifierCommandes(id: number): Observable<any> {
        return this.http.put<any>(`${this.apiUrl}/solutions/${id}/planifier`, {});
    }
}