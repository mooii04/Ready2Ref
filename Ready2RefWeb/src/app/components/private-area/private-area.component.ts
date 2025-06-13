import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { UserService } from '../../../services/user.service';
import { UserProfile, UserRole } from '../../../model/user.model';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-private-area',
  templateUrl: './private-area.component.html',
  styleUrls: ['./private-area.component.css']
})
export class PrivateAreaComponent implements OnInit {

  userProfile: UserProfile | null = null;
  rol: UserRole | null = null;

  constructor(
    private authService: AuthService,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit(): void {
  const token = localStorage.getItem('accessToken');
  console.log('Access token:', token);

  if (token) {
    try {
      const decoded: any = jwtDecode(token);
      console.log('Token decodificado:', decoded);
    } catch (err) {
      console.error('Error al decodificar token:', err);
    }
  }

  this.userService.fetchUserProfile().subscribe({
    next: (profile) => {
      this.userProfile = profile;
      if (profile.roles && profile.roles.length > 0) {
        this.rol = profile.roles[0];
      }
    },
    error: (err) => {
      console.error('Error al cargar perfil', err);
      this.router.navigate(['/login']);
    }
  });
}

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  gestionarUsuarios(): void {
    console.log('Gestionar Usuarios');
    // this.router.navigate(['/admin/users']);
  }

  crearPack(): void {
    console.log('Crear Pack');
    // this.router.navigate(['/admin/create-pack']);
  }

  verPacks(): void {
    console.log('Ver Packs');
    // this.router.navigate(['/user/packs']);
  }

  verMensajes(): void {
    console.log('Ver Mensajes');
    // this.router.navigate(['/user/messages']);
  }

  verEntrenamientos(): void {
    console.log('Ver Entrenamientos');
    // this.router.navigate(['/entrenador/entrenamientos']);
  }

  crearEntrenamiento(): void {
    console.log('Crear Entrenamiento');
    // this.router.navigate(['/entrenador/crear-entrenamiento']);
  }
}
