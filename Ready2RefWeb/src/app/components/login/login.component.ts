import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm: FormGroup;
  welcomeMessage: string = 'Inicia sesión';

  constructor(private fb: FormBuilder, private userService: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    if (this.loginForm.valid) {
      const body = this.loginForm.value;

      this.userService.userLogin(body).subscribe({
        next: (response) => {
          console.log('Login exitoso:', response);

          localStorage.setItem('accessToken', response.token);
          localStorage.setItem('refreshToken', response.refreshToken);
          alert('Bienvenido, ${response.username}');
          this.router.navigate(['/home']); 
        },
        error: (error) => {
          if (error.status === 401) {
            alert('Credenciales incorrectas. Por favor, verifica tu usuario y contraseña.');
          } else {
            console.error('Error inesperado:', error);
          }
        }
      });
    } else {
      alert('Por favor, completa todos los campos.');
    }
  }

}
