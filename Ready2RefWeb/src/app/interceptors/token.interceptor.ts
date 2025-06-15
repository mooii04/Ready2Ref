import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError, switchMap, catchError } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService, private router: Router) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getToken();

    // Adjunta el token si existe
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        // Si es un 401 y el mensaje contiene "JWT expired"
        if (error.status === 401 && error.error?.detail?.includes('JWT expired')) {
          return this.authService.refreshToken().pipe(
            switchMap((res: any) => {
              // Guardar los nuevos tokens
              this.authService.saveTokens(res.token, res.refreshToken);

              // Repetir la petición original con el nuevo token
              const retryRequest = request.clone({
                setHeaders: {
                  Authorization: `Bearer ${res.token}`
                }
              });
              return next.handle(retryRequest);
            }),
            catchError(err => {
              // Si falla el refresh, borrar tokens y redirigir al login
              this.authService.logout();
              this.router.navigate(['/login']);
              return throwError(() => err);
            })
          );
        }

        // Si no es un 401 o no es por expiración, pasar el error
        return throwError(() => error);
      })
    );
  }
}
