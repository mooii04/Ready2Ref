import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class EntrenadorService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getEntrenamientos(): Observable<any> {
    return this.http.get(`${this.apiUrl}/entrenador/entrenamientos`);
  }
  uploadEntrenamiento(entrenamiento: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/entrenador/upload`, entrenamiento);
  }
}
