import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  welcomeMessage: string = '¡Te damos la bienvenida a nuestra web!';

  // Puedes agregar más lógica aquí si lo necesitas
}
