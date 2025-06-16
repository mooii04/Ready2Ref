// arbitros-registrados.component.ts

import { Component, OnInit } from '@angular/core';
import { Arbitro } from '../../../model/arbitro.model';
import { ArbitroService } from '../../../services/arbitro.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-arbitros-registrados',
  templateUrl: './arbitros-registrados.component.html',
  styleUrls: ['./arbitros-registrados.component.css']
})
export class ArbitrosRegistradosComponent implements OnInit {
  arbitros: Arbitro[] = [];
  loading = false;
  error: string | null = null;

  // Filtros
  filtroNombre = '';
  filtroCategoria = '';

  // Eliminación
  eliminandoUsername: string | null = null;

  constructor(private arbitroService: ArbitroService, private http: HttpClient, private router: Router) {}

  ngOnInit() {
    this.cargarArbitros();
  }

  cargarArbitros() {
    this.loading = true;
    const search = this.buildSearchQuery();

    this.arbitroService.getArbitros(0, 50, search).subscribe({
      next: (res) => {
        this.arbitros = res.content || [];
        this.loading = false;
      },
      error: () => {
        this.error = 'Error al cargar árbitros';
        this.loading = false;
      }
    });
  }

  buildSearchQuery(): string {
    let query = '';
    if (this.filtroNombre.trim()) {
      query += `nombre:${this.filtroNombre.trim()},`;
    }
    if (this.filtroCategoria.trim()) {
      query += `categoria:${this.filtroCategoria.trim()},`;
    }
    return query;
  }

  limpiarFiltros() {
    this.filtroNombre = '';
    this.filtroCategoria = '';
    this.cargarArbitros();
  }

  abrirDialogoEliminar(username: string) {
    this.eliminandoUsername = username;
  }

  cerrarDialogoEliminar() {
    this.eliminandoUsername = null;
  }

  confirmarEliminar() {
    if (this.eliminandoUsername) {
      this.http.delete(`http://localhost:8080/delete/${this.eliminandoUsername}`).subscribe({
        next: () => {
          this.cerrarDialogoEliminar();
          this.cargarArbitros();
        },
        error: (err) => {
          alert('Error al eliminar el usuario: ' + (err?.error?.message || 'Error desconocido'));
          this.cerrarDialogoEliminar();
        }
      });
    }
  }
}
