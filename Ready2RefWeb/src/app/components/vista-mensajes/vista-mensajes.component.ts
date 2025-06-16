import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Mensaje {
  id: number; // Asegúrate de que la interfaz tenga el campo 'id'
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
  mensajes: any[] = [];
  loading = false;
  error: string | null = null;

  get mensajesNoLeidos(): number {
    return this.mensajes.filter(m => !m.leido).length;
  }

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.cargarMensajes();
  }

  marcarComoLeido(index: number, event: MouseEvent) {
    event.stopPropagation();
    const mensaje = this.mensajes[index];
    if (!mensaje.leido && mensaje.id) {
      // Para depuración, muestra el id y la URL en consola
      console.log('Intentando marcar como leído:', mensaje.id, `http://localhost:8080/mensaje/${mensaje.id}/leido`);
      this.http.put(`http://localhost:8080/mensaje/${mensaje.id}/leido`, {}, { responseType: 'text' }).subscribe({
        next: () => {
          this.mensajes[index].leido = true;
          // Opcional: vuelve a cargar mensajes si quieres refrescar el contador global
          // this.cargarMensajes();
        },
        error: (err) => {
          this.error = 'No se pudo marcar como leído.';
          console.error(err);
        }
      });
    } else {
      if (!mensaje.id) {
        console.error('El mensaje no tiene id:', mensaje);
      }
    }
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
}
