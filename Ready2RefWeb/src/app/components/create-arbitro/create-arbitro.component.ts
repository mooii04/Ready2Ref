import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-arbitro',
  templateUrl: './create-arbitro.component.html',
  styleUrls: ['./create-arbitro.component.css']
})
export class CreateArbitroComponent {
  arbitroForm: FormGroup;
  tipo: 'USER' | 'ADMIN' | null = null; // Asegúrate de que la variable se llama 'tipo'
  loading = false;
  error: string | null = null;
  success: string | null = null;

  tallas = ['XS', 'S', 'M', 'L', 'XL', 'XXL'];

  categorias = [
    'AUXILIAR',
    'OFICIAL',
    'PROVINCIAL',
    'DIVISION_HONOR',
    'ASISTENTE_3RFEF',
    'RFEF3',
    'ASISTENTE_2RFEF',
    'RFEF2',
    'ASISTENTE_1RFEF',
    'RFEF1',
    'ASISTENTE_SEGUNDA',
    'SEGUNDA',
    'ASISTENTE_PRIMERA',
    'PRIMERA'
    // Añade aquí todas las categorías de tu enum
  ];

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    this.arbitroForm = this.fb.group({
      nombre: ['', Validators.required],
      primerApellido: ['', Validators.required],
      segundoApellido: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefono: ['', Validators.required],
      password: ['', Validators.required],
      verifyPassword: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
      edad: ['', Validators.required],
      categoria: ['', Validators.required],
      fechaInscripcion: ['', Validators.required],
      tallaBotas: ['', Validators.required],
      tallaCamiseta: ['', Validators.required],
      tallaCalzonas: ['', Validators.required],
      tallaChandal: ['', Validators.required],
      foto: ['', Validators.required],
      roles: [{ value: '', disabled: true }, Validators.required]
    });
  }

  seleccionarTipo(tipo: 'USER' | 'ADMIN') {
    this.tipo = tipo;
    this.arbitroForm.patchValue({ roles: tipo });
  }

  onSubmit() {
    this.arbitroForm.markAllAsTouched();
    if (this.arbitroForm.invalid || !this.tipo) {
      this.error = 'Por favor, rellena todos los campos obligatorios.';
      return;
    }
    this.loading = true;
    this.error = null;
    this.success = null;

    const raw = this.arbitroForm.getRawValue();

    const payload = {
      ...raw,
      edad: Number(raw.edad),
      tallaBotas: Number(raw.tallaBotas),
      roles: this.tipo,
      fechaNacimiento: raw.fechaNacimiento ? raw.fechaNacimiento.toString().slice(0, 10) : '',
      fechaInscripcion: raw.fechaInscripcion ? raw.fechaInscripcion.toString().slice(0, 10) : '',
      categoria: raw.categoria,
      tallaCamiseta: raw.tallaCamiseta,
      tallaCalzonas: raw.tallaCalzonas,
      tallaChandal: raw.tallaChandal
    };

    const url = this.tipo === 'USER'
      ? 'http://localhost:8080/arbitro/create/user'
      : 'http://localhost:8080/arbitro/create/admin';

    interface CreateArbitroResponse {
      activationToken?: string;
      // ...otros campos si los necesitas...
    }

    this.http.post<CreateArbitroResponse>(url, payload).subscribe({
      next: (res) => {
        if (res && res.activationToken) {
          this.http.post('http://localhost:8080/activate/account', { token: res.activationToken }).subscribe({
            next: () => {
              this.success = 'Árbitro creado y cuenta activada correctamente';
              this.loading = false;
              setTimeout(() => this.router.navigate(['/areaprivada']), 1500);
            },
            error: (err) => {
              this.success = 'Árbitro creado, pero error al activar la cuenta';
              this.error = err?.error?.message || 'Error al activar la cuenta';
              this.loading = false;
            }
          });
        } else {
          this.success = 'Árbitro creado correctamente';
          this.loading = false;
          setTimeout(() => this.router.navigate(['/areaprivada']), 1500);
        }
      },
      error: (err) => {
        this.error = err?.error?.message || 'Error al crear árbitro';
        this.loading = false;
      }
    });
  }
}