import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Mensaje {
  id: number;
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

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.cargarMensajes();
  }

  get mensajesNoLeidos(): number {
    return this.mensajes.filter(m => !m.leido).length;
  }

  cargarMensajes() {
    this.loading = true;
    this.http.get<any[]>('http://localhost:8080/mensaje/search').subscribe({
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
    if (!mensaje.leido && mensaje.id) {
      this.http.put(`http://localhost:8080/mensaje/${mensaje.id}/leido`, {}).subscribe({
        next: (res: any) => {
          // Actualiza solo el mensaje leído en el array local para respuesta rápida
          this.mensajes[index].leido = true;
          // Opcional: si quieres recargar todos los mensajes desde el backend, descomenta la siguiente línea
          // this.cargarMensajes();
        },
        error: (err) => {
          this.error = 'No se pudo marcar como leído.';
        }
      });
    }
  }

  esMensajeDeEntrenamiento(mensaje: Mensaje): boolean {
    return mensaje.asunto.toLowerCase().includes('entrenamiento');
  }

  descargarEntrenamiento(id: number, event: MouseEvent) {
    event.stopPropagation();
    const url = `http://localhost:8080/download/${id}`;
    this.http.get(url, { responseType: 'blob' }).subscribe({
      next: (blob) => {
        const a = document.createElement('a');
        a.href = URL.createObjectURL(blob);
        a.download = `entrenamiento-${id}.pdf`;
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
