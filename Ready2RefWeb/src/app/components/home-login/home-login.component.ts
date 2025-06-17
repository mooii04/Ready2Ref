import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { UserService } from '../../../services/user.service';
import { UserProfile } from '../../../model/user.model';

@Component({
  selector: 'app-home-login',
  templateUrl: './home-login.component.html',
  styleUrls: ['./home-login.component.css']
})
export class HomeLoginComponent implements OnInit {
  userName: string = 'Usuario';

  accesos = [
    { titulo: 'Entrenamientos', descripcion: 'Accede a tus entrenamientos recientes', icon: 'bi bi-activity', link: '/entrenamientos' },
    { titulo: 'Packs de ropa', descripcion: 'Consulta tus pedidos y tallas', icon: 'bi bi-bag', link: '/packs' },
    { titulo: 'Eventos', descripcion: 'Próximos eventos y reuniones', icon: 'bi bi-calendar-event', link: '/eventos' },
    { titulo: 'Comunidad', descripcion: 'Conecta con otros árbitros', icon: 'bi bi-people', link: '/comunidad' },
    { titulo: 'Perfil', descripcion: 'Gestiona tu cuenta', icon: 'bi bi-person', link: '/perfil' }
  ];

  constructor(
    private authService: AuthService,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit() {
    // Ejemplo: si guardas el nombre en localStorage tras login
    const user = JSON.parse(localStorage.getItem('user') || '{}');
    this.userName = user.nombre || user.username || 'Usuario';
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
