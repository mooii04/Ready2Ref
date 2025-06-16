import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../../services/user.service';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-edit-all-admin',
  templateUrl: './edit-all-admin.component.html',
  styleUrls: ['./edit-all-admin.component.css']
})
export class EditAllAdminComponent implements OnInit {
  datosForm: FormGroup;
  loading = false;
  error: string | null = null;
  success: string | null = null;
  cargandoDatos = true;
  username: string = '';
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
  ];
  tallas = ['XS', 'S', 'M', 'L', 'XL', 'XXL'];

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private userService: UserService,
    private route: ActivatedRoute,
    private authService: AuthService
  ) {
    this.datosForm = this.fb.group({
      nombre: ['', Validators.required],
      primerApellido: ['', Validators.required],
      segundoApellido: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefono: ['', Validators.required],
      categoria: [''],
      tallaBotas: [''],
      tallaCamiseta: [''],
      tallaCalzonas: [''],
      tallaChandal: [''],
      foto: ['']
    });
  }

  ngOnInit(): void {

    localStorage.getItem('accessToken');

    this.route.paramMap.subscribe(params => {
      const username = params.get('username');
      if (!username) {
        this.error = 'Nombre de usuario no proporcionado en la ruta.';
        return;
      }

      this.username = username;
      const url = `http://localhost:8080/admin/${this.username}`;

      this.http.get<any>(url).subscribe({
        next: (data) => {
          this.datosForm.patchValue({
            nombre: data.nombre,
            primerApellido: data.primerApellido,
            segundoApellido: data.segundoApellido,
            email: data.email,
            telefono: data.telefono,
            categoria: data.categoria || '',
            tallaBotas: data.tallaBotas || '',
            tallaCamiseta: data.tallaCamiseta || '',
            tallaCalzonas: data.tallaCalzonas || '',
            tallaChandal: data.tallaChandal || '',
            foto: data.foto || ''
          });
          this.cargandoDatos = false;
        },
        error: (err) => {
          this.error = 'No se pudieron cargar los datos del usuario.';
          console.error(err);
          this.cargandoDatos = false;
        }
      });
    });

    this.cargarMensajesNoLeidos();
  }

  cargarMensajesNoLeidos() {
    // Lógica opcional para cargar mensajes no leídos
  }

  onSubmit() {
  this.datosForm.markAllAsTouched();
  if (this.datosForm.invalid) {
    this.error = 'Por favor, rellena todos los campos obligatorios.';
    return;
  }

  // Verifica que el usuario esté autenticado antes de hacer la petición
  if (!this.authService.isLoggedIn()) {
    this.error = 'No estás autenticado. Por favor, inicia sesión.';
    this.router.navigate(['/login']);
    return;
  }

  this.loading = true;
  this.error = null;
  this.success = null;

  const url = `http://localhost:8080/arbitro/edit/admin/${this.username}`;
  this.http.put(url, this.datosForm.value).subscribe({
    next: () => {
      this.success = 'Datos personales actualizados correctamente';
      this.loading = false;
      setTimeout(() => this.router.navigate(['/private-area']), 1500);
    },
    error: (err) => {
      if (err.status === 401) {
        this.error = 'No autorizado. Asegúrate de estar autenticado como administrador.';
      } else if (err.status === 403) {
        this.error = 'No tienes permisos para editar este usuario.';
      } else if (err.status === 404) {
        this.error = 'Usuario no encontrado.';
      } else {
        this.error = err?.error?.message || 'Error al actualizar datos';
      }
      this.loading = false;
    }
  });
}

}
