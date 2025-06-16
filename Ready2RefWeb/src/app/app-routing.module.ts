import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PrivateAreaComponent } from './components/private-area/private-area.component';
import { HomeLoginComponent } from './components/home-login/home-login.component';
import { AuthGuard } from './guards/auth.guard';
import { CreateArbitroComponent } from './components/create-arbitro/create-arbitro.component';
import { ArbitrosRegistradosComponent } from './components/arbitros-registrados/arbitros-registrados.component';
import { CreateEntrenadorComponent } from './components/create-entrenador/create-entrenador.component';
import { EditarDatosPersonalesComponent } from './components/user-editar-datos-personales/editar-datos-personales.component';
import { EditarEntrenadorDatosComponent } from './components/editar-entrenador-datos/editar-entrenador-datos.component';
import { CambiarContraseniaComponent } from './components/cambiar-contrasenia/cambiar-contrasenia.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'areaprivada', component: PrivateAreaComponent, canActivate: [AuthGuard] },
  { path: 'home-login', component: HomeLoginComponent },

  { path : 'user-edit', component: EditarDatosPersonalesComponent},

  {path: 'entrenador-edit', component: EditarEntrenadorDatosComponent},

  { path: 'contrasenia', component: CambiarContraseniaComponent},

  // ADMIN
  { path: 'arbitro/create/user', component: CreateArbitroComponent },
  { path: 'arbitro/create/admin', component: CreateArbitroComponent },
  { path: 'arbitros-registrados', component: ArbitrosRegistradosComponent },

  // ENTRENADOR
  { path: 'crear-entrenador', component: CreateEntrenadorComponent },

  // ...otras rutas...
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }