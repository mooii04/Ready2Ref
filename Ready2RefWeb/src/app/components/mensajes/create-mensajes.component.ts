import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-mensajes',
  templateUrl: './create-mensajes.component.html',
  styleUrls: ['./create-mensajes.component.css']
})
export class CreateMensajesComponent {
  mensajeForm: FormGroup;
  loading = false;
  error: string | null = null;
  success: string | null = null;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    this.mensajeForm = this.fb.group({
      asunto: ['', Validators.required],
      contenido: ['', Validators.required]
    });
  }

  onSubmit() {
    this.mensajeForm.markAllAsTouched();
    if (this.mensajeForm.invalid) {
      this.error = 'Por favor, rellena todos los campos obligatorios.';
      return;
    }
    this.loading = true;
    this.error = null;
    this.success = null;

    this.http.post('http://localhost:8080/mensaje/create/admin', this.mensajeForm.value).subscribe({
      next: () => {
        this.success = 'Mensaje creado correctamente';
        this.loading = false;
        this.router.navigate(['/areaprivada']);
      },
      error: (err) => {
        this.error = err?.error?.message || 'Error al crear el mensaje';
        this.loading = false;
      }
    });
  }
}
