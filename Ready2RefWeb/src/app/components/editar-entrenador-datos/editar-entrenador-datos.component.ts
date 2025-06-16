import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-editar-entrenador-datos',
  templateUrl: './editar-entrenador-datos.component.html',
  styleUrls: ['./editar-entrenador-datos.component.css']
})
export class EditarEntrenadorDatosComponent implements OnInit {
  datosForm: FormGroup;
  loading = false;
  error: string | null = null;
  success: string | null = null;
  cargandoDatos = true;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    this.datosForm = this.fb.group({
      nombre: ['', Validators.required],
      primerApellido: ['', Validators.required],
      segundoApellido: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefono: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.cargandoDatos = true;
    this.http.get<any>('http://localhost:8080/me/entrenador').subscribe({
      next: (data) => {
        this.datosForm.patchValue({
          nombre: data.nombre,
          primerApellido: data.primerApellido,
          segundoApellido: data.segundoApellido,
          email: data.email,
          telefono: data.telefono
        });
        this.cargandoDatos = false;
      },
      error: () => {
        this.error = 'No se pudieron cargar los datos personales.';
        this.cargandoDatos = false;
      }
    });
  }

  onSubmit() {
    this.datosForm.markAllAsTouched();
    if (this.datosForm.invalid) {
      this.error = 'Por favor, rellena todos los campos obligatorios.';
      return;
    }
    this.loading = true;
    this.error = null;
    this.success = null;

    const payload = { ...this.datosForm.value };

    this.http.put('http://localhost:8080/entrenador/edit/me', payload).subscribe({
      next: () => {
        this.success = 'Datos personales actualizados correctamente';
        this.loading = false;
        setTimeout(() => this.router.navigate(['/areaprivada']), 1500);
      },
      error: (err) => {
        this.error = err?.error?.message || 'Error al actualizar datos';
        this.loading = false;
      }
    });
  }
}

