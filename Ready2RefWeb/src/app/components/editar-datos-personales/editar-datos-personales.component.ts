import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-editar-datos-personales',
  templateUrl: './editar-datos-personales.component.html',
  styleUrls: ['./editar-datos-personales.component.css']
})
export class EditarDatosPersonalesComponent implements OnInit {
  datosForm: FormGroup;
  loading = false;
  error: string | null = null;
  success: string | null = null;
  cargandoDatos = true;

  tallas = ['XS', 'S', 'M', 'L', 'XL', 'XXL'];

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    this.datosForm = this.fb.group({
      nombre: ['', Validators.required],
      primerApellido: ['', Validators.required],
      segundoApellido: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefono: ['', Validators.required],
      tallaBotas: ['', Validators.required],
      tallaCamiseta: ['', Validators.required],
      tallaCalzonas: ['', Validators.required],
      tallaChandal: ['', Validators.required],
      foto: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.cargandoDatos = true;
    this.http.get<any>('http://localhost:8080/me/user').subscribe({
      next: (data) => {
        this.datosForm.patchValue({
          nombre: data.nombre,
          primerApellido: data.primerApellido,
          segundoApellido: data.segundoApellido,
          email: data.email,
          telefono: data.telefono,
          tallaBotas: data.tallaBotas,
          tallaCamiseta: data.tallaCamiseta,
          tallaCalzonas: data.tallaCalzonas,
          tallaChandal: data.tallaChandal,
          foto: data.foto
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

    const payload = {
      ...this.datosForm.value,
      tallaBotas: Number(this.datosForm.value.tallaBotas)
    };

    this.http.put('http://localhost:8080/arbitro/edit/user/me', payload).subscribe({
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
