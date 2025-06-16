import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Mensaje {
  asunto: string;
  contenido: string;
  fechaEnvio: string;
  leido: boolean;
}

@Component({
  selector: 'app-vista-mensajes',
  templateUrl: './vista-mensajes.component.html',
  styleUrls: ['./vista-mensajes.component.css']
})
export class VistaMensajesComponent implements OnInit {
  mensajes: Mensaje[] = [];
  loading = false;
  error: string | null = null;

  get mensajesNoLeidos(): number {
    return this.mensajes.filter(m => !m.leido).length;
  }

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.cargarMensajes();
  }

  cargarMensajes() {
    this.loading = true;
    this.http.get<Mensaje[]>('http://localhost:8080/mensaje/search').subscribe({
      next: (data) => {
        this.mensajes = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudieron cargar los mensajes.';
        this.loading = false;
      }
    });
  }

  marcarComoLeido(index: number) {
    this.mensajes[index].leido = true;
  }
}
