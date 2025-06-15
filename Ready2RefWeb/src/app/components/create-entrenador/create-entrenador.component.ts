import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-entrenador',
  templateUrl: './create-entrenador.component.html',
  styleUrls: ['./create-entrenador.component.css']
})
export class CreateEntrenadorComponent {
  entrenadorForm: FormGroup;
  loading = false;
  error: string | null = null;
  success: string | null = null;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    this.entrenadorForm = this.fb.group({
      nombre: ['', Validators.required],
      primerApellido: ['', Validators.required],
      segundoApellido: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefono: ['', Validators.required],
      password: ['', Validators.required],
      verifyPassword: ['', Validators.required]
    });
  }

  onSubmit() {
    this.entrenadorForm.markAllAsTouched();

    // Revisa si hay errores de validación
    if (this.entrenadorForm.invalid) {
      this.error = 'Por favor, rellena todos los campos obligatorios.';
      return;
    }

    this.loading = true;
    this.error = null;
    this.success = null;

    // Asegúrate de enviar todos los campos correctamente
    const payload = { ...this.entrenadorForm.value };

    this.http.post('http://localhost:8080/entrenador/create', payload).subscribe({
      next: (res) => {
        this.success = 'Entrenador creado correctamente';
        this.loading = false;
        setTimeout(() => this.router.navigate(['/areaprivada']), 1500);
      },
      error: (err) => {
        // Muestra el mensaje de error real si lo hay
        this.error = err?.error?.message || 'Error al crear entrenador';
        this.loading = false;
      }
    });
  }
}
