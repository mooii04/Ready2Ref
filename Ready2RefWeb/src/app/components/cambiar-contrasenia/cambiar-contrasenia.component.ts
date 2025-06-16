import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cambiar-contrasenia',
  templateUrl: './cambiar-contrasenia.component.html',
  styleUrls: ['./cambiar-contrasenia.component.css']
})
export class CambiarContraseniaComponent {
  form: FormGroup;
  loading = false;
  error: string | null = null;
  success: string | null = null;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    this.form = this.fb.group({
      oldPassword: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
      verifyPassword: ['', Validators.required]
    });
  }

  onSubmit() {
    this.form.markAllAsTouched();
    if (this.form.invalid) {
      this.error = 'Por favor, rellena todos los campos correctamente.';
      return;
    }
    if (this.form.value.password !== this.form.value.verifyPassword) {
      this.error = 'Las contraseñas nuevas no coinciden.';
      return;
    }
    this.loading = true;
    this.error = null;
    this.success = null;

    this.http.put('http://localhost:8080/edit/contrasenia', this.form.value).subscribe({
      next: () => {
        this.success = 'Contraseña cambiada correctamente';
        this.loading = false;
        setTimeout(() => this.router.navigate(['/areaprivada']), 1500);
      },
      error: (err) => {
        this.error = err?.error?.message || 'Error al cambiar la contraseña';
        this.loading = false;
      }
    });
  }
}
