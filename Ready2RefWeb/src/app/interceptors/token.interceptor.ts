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

    // Sólo añadir el token si existe y la URL NO es login, refresh token ni descarga pública
    if (
      token &&
      !request.url.includes('/auth/login') &&
      !request.url.includes('/auth/refresh/token') &&
      !request.url.includes('/download/')
    ) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        // Manejar error 401 por token expirado
        if (error.status === 401 && error.error?.detail?.includes('JWT expired')) {
          return this.authService.refreshToken().pipe(
            switchMap((res: any) => {
              // Guardar nuevos tokens
              this.authService.saveTokens(res.token, res.refreshToken);

              // Reintentar la petición original con el nuevo token
              const retryRequest = request.clone({
                setHeaders: {
                  Authorization: `Bearer ${res.token}`
                }
              });
              return next.handle(retryRequest);
            }),
            catchError(err => {
              // Si falla refresh, limpiar sesión y redirigir a login
              this.authService.logout();
              this.router.navigate(['/login']);
              return throwError(() => err);
            })
          );
        }

        // Para otros errores, devolver error normal
        return throwError(() => error);
      })
    );
  }
}
