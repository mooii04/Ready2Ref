import { Component, NgModule, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  welcomeMessage: string = '¡Te damos la bienvenida a nuestra web!';
  userName: string = 'Alex'; // Cambia esto por el nombre real del usuario si lo tienes

  ngOnInit() {
    // Si tienes el nombre real del usuario, asígnalo aquí
    // this.userName = this.authService.getUserName() || 'Alex';
  }

  // Puedes agregar más lógica aquí si lo necesitas
}
