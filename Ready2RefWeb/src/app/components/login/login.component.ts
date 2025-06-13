import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm: FormGroup;
  welcomeMessage: string = 'Inicia sesión';
  loginError: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    this.loginError = null;
    if (this.loginForm.valid) {
      const body = this.loginForm.value;

      this.authService.login(body.username, body.password).subscribe({
        next: (response: any) => {
          localStorage.setItem('user', JSON.stringify(response));
          localStorage.setItem('accessToken', response.token);
          localStorage.setItem('refreshToken', response.refreshToken);
          localStorage.setItem('username', response.username);
          this.router.navigate(['/home-login']);
        },
        error: (error: { status: number; }) => {
          if (error.status === 401) {
            this.loginError = 'Credenciales incorrectas. Por favor, verifica tu usuario y contraseña.';
          } else {
            this.loginError = 'Error inesperado. Inténtalo de nuevo más tarde.';
            console.error('Error inesperado:', error);
          }
        }
      });
    } else {
      this.loginError = 'Por favor, completa todos los campos.';
    }
  }
}
