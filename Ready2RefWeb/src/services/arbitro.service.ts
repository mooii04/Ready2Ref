// src/app/services/arbitro.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Arbitro } from '../model/arbitro.model'; // Asegúrate de que la ruta sea correcta

@Injectable({ providedIn: 'root' })
export class ArbitroService {
  private baseUrl = 'http://localhost:8080/arbitro';

  constructor(private http: HttpClient) {}

  // arbitro.service.ts

getArbitros(pagina = 0, tamano = 50, search?: string): Observable<{ content: Arbitro[] }> {
  let url = `${this.baseUrl}/search/?pagina=${pagina}&tamano=${tamano}`;
  if (search) {
    url += `&search=${encodeURIComponent(search)}`;
  }
  return this.http.get<{ content: Arbitro[] }>(url);
}

}
