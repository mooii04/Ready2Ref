import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Mensaje {
  id: number;
  asunto: string;
  contenido: string;
  fechaEnvio: string;
  leido: boolean;
  archivoEntrenamientoId?: string;
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

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.cargarMensajes();
  }

  get mensajesNoLeidos(): number {
    return this.mensajes.filter(m => !m.leido).length;
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

  marcarComoLeido(index: number, event: MouseEvent) {
    event.stopPropagation();
    const mensaje = this.mensajes[index];
    if (!mensaje.leido) {
      this.http.put(`http://localhost:8080/mensaje/${mensaje.id}/leido`, {}).subscribe({
        next: () => {
          this.mensajes[index].leido = true;
        },
        error: () => {
          this.error = 'No se pudo marcar como leído.';
        }
      });
    }
  }

  esMensajeDeEntrenamiento(mensaje: Mensaje): boolean {
  return mensaje.archivoEntrenamientoId !== undefined && mensaje.archivoEntrenamientoId !== null && mensaje.archivoEntrenamientoId !== '';
}

 descargarEntrenamiento(fileId: string, event: MouseEvent) {
  event.stopPropagation();
  const url = `http://localhost:8080/download/${encodeURIComponent(fileId)}`;
  this.http.get(url, { responseType: 'blob' }).subscribe({
    next: (blob) => {
      const a = document.createElement('a');
      a.href = URL.createObjectURL(blob);
      a.download = fileId;
      a.click();
      URL.revokeObjectURL(a.href);
    },
    error: (err) => {
      this.error = 'No se pudo descargar el entrenamiento.';
      console.error(err);
    }
  });
}

}
