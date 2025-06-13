import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AdminService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getAllUsers(): Observable<any> {
    return this.http.get(`${this.apiUrl}/me/admin`);
  }
  createArbitro(arbitro: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/arbitro/create`, arbitro);
  }
  createEntrenador(entrenador: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/entrenador/create`, entrenador);
  }
  deleteUser(userId: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete/${userId}`);
  }
}
