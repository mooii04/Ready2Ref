// src/app/services/auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { UserLogin } from '../model/login.model';
import { jwtDecode as jwt_decode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080'; // URL del backend

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    return this.http.post<UserLogin>(`${this.apiUrl}/auth/login`, { username, password })
      .pipe(
        tap((res: UserLogin) => {
          localStorage.setItem('user', JSON.stringify(res));
          localStorage.setItem('accessToken', res.token);
          localStorage.setItem('refreshToken', res.refreshToken);
          localStorage.setItem('username', res.username);
        })
      );
  }

  logout(): void {
    localStorage.clear(); // Borra todo por simplicidad
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    if (!token) return false;

    try {
      const decoded: any = jwt_decode(token);
      const now = Math.floor(Date.now() / 1000);
      return decoded.exp && decoded.exp > now;
    } catch (e) {
      console.error('Token inválido o corrupto:', e);
      return false;
    }
  }

  getUsername(): string | null {
    return localStorage.getItem('username');
  }

  getToken(): string | null {
    return localStorage.getItem('accessToken');
  }

  isLoggedIn(): boolean {
    return this.isAuthenticated() && !!this.getToken();
  }
}
