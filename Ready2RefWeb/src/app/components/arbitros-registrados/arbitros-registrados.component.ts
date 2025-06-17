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
  filtroEmail = '';
  filtroTelefono = '';

  // Eliminación
  eliminandoUsername: string | null = null;

  // Paginación
  pagina = 0;
  tamano = 10;
  totalPaginas = 1;
  totalElementos = 0;

  constructor(private arbitroService: ArbitroService, private http: HttpClient, private router: Router) {}

  ngOnInit() {
    this.cargarArbitros();
  }

  cargarArbitros() {
    this.loading = true;
    const search = this.buildSearchQuery();

    this.arbitroService.getArbitros(this.pagina, this.tamano, search).subscribe({
      next: (res: any) => {
        this.arbitros = res.content || [];
        this.totalPaginas = res.totalPages || 1;
        this.totalElementos = res.totalElements || 0;
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
    if (this.filtroEmail.trim()) {
      query += `email:${this.filtroEmail.trim()},`;
    }
    if (this.filtroTelefono.trim()) {
      query += `telefono:${this.filtroTelefono.trim()},`;
    }
    return query;
  }

  limpiarFiltros() {
    this.filtroNombre = '';
    this.filtroCategoria = '';
    this.filtroEmail = '';
    this.filtroTelefono = '';
    this.pagina = 0;
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

  paginaAnterior() {
    if (this.pagina > 0) {
      this.pagina--;
      this.cargarArbitros();
    }
  }

  paginaSiguiente() {
    if (this.pagina < this.totalPaginas - 1) {
      this.pagina++;
      this.cargarArbitros();
    }
  }

  irAPagina(p: number) {
    if (p >= 0 && p < this.totalPaginas) {
      this.pagina = p;
      this.cargarArbitros();
    }
  }

  getFotoUrl(foto: string | null | undefined): string {
    if (foto && foto.trim() !== '') {
      return 'http://localhost:8080/uploads/' + foto;
    }
    return 'assets/default-avatar.png';
  }
}
