// arbitros-registrados.component.ts

import { Component, OnInit } from '@angular/core';
import { Arbitro } from '../../../model/arbitro.model';
import { ArbitroService } from '../../../services/arbitro.service';

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

  constructor(private arbitroService: ArbitroService) {}

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
}
