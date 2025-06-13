import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PrivateAreaComponent } from './components/private-area/private-area.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { MensajesComponent } from './components/mensajes/mensajes.component';
import { PacksComponent } from './components/packs/packs.component';
import { EntrenamientosComponent } from './components/entrenamientos/entrenamientos.component';
import { AdminUsuariosComponent } from './components/admin-usuarios/admin-usuarios.component';
import { AdminArbitroCrearComponent } from './components/admin-arbitro-crear/admin-arbitro-crear.component';
import { AdminEntrenadorCrearComponent } from './components/admin-entrenador-crear/admin-entrenador-crear.component';
import { AdminUsuarioEliminarComponent } from './components/admin-usuario-eliminar/admin-usuario-eliminar.component';
import { EntrenadorEntrenamientosComponent } from './components/entrenador-entrenamientos/entrenador-entrenamientos.component';
import { EntrenadorEntrenamientoSubirComponent } from './components/entrenador-entrenamiento-subir/entrenador-entrenamiento-subir.component';
import { HomeLoginComponent } from './components/home-login/home-login.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'areaprivada', component: PrivateAreaComponent, canActivate: [AuthGuard] },
  { path: 'home-login', component: HomeLoginComponent },

  // USER
  { path: 'perfil', component: PerfilComponent },
  { path: 'mensajes', component: MensajesComponent },
  { path: 'packs', component: PacksComponent },
  { path: 'entrenamientos', component: EntrenamientosComponent },

  // ADMIN
  { path: 'admin/usuarios', component: AdminUsuariosComponent },
  { path: 'admin/arbitro/crear', component: AdminArbitroCrearComponent },
  { path: 'admin/entrenador/crear', component: AdminEntrenadorCrearComponent },
  { path: 'admin/usuario/eliminar', component: AdminUsuarioEliminarComponent },

  // ENTRENADOR
  { path: 'entrenador/entrenamientos', component: EntrenadorEntrenamientosComponent },
  { path: 'entrenador/entrenamiento/subir', component: EntrenadorEntrenamientoSubirComponent },

  // ...otras rutas...
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }