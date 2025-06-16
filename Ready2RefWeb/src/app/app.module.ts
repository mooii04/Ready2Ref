import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, provideHttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderPublicComponent } from './components/header-public/header-public.component';
import { PrivateAreaComponent } from './components/private-area/private-area.component';
import { HeaderPrivateComponent } from './components/header-private/header-private.component';
import { HomeLoginComponent } from './components/home-login/home-login.component';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { CreateArbitroComponent } from './components/create-arbitro/create-arbitro.component';
import { ArbitrosRegistradosComponent } from './components/arbitros-registrados/arbitros-registrados.component';
import { CreateEntrenadorComponent } from './components/create-entrenador/create-entrenador.component';
import { EditarDatosPersonalesComponent } from './components/user-editar-datos-personales/editar-datos-personales.component';
import { EditarEntrenadorDatosComponent } from './components/editar-entrenador-datos/editar-entrenador-datos.component';
import { CambiarContraseniaComponent } from './components/cambiar-contrasenia/cambiar-contrasenia.component';
import { EntrenamientosComponent } from './components/entrenamientos/entrenamientos.component';
import { VistaMensajesComponent } from './components/vista-mensajes/vista-mensajes.component';
import { CreateMensajesComponent } from './components/mensajes/create-mensajes.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    HeaderPrivateComponent,
    HeaderPublicComponent,
    PrivateAreaComponent,
    HomeLoginComponent,
    CreateArbitroComponent,
    ArbitrosRegistradosComponent,
    CreateEntrenadorComponent,
    EditarDatosPersonalesComponent,
    EditarEntrenadorDatosComponent,
    CambiarContraseniaComponent,
    EntrenamientosComponent,
    VistaMensajesComponent,
    CreateMensajesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [
    provideAnimationsAsync(),
    provideHttpClient(),
    //{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
