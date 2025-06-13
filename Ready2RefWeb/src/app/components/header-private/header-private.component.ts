import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';
import { UserService } from '../../../services/user.service';

@Component({
    selector: 'app-header-private',
    templateUrl: './header-private.component.html',
    styleUrls: ['./header-private.component.css']
})
export class HeaderPrivateComponent {

constructor(
    private authService: AuthService,
    private router: Router,
    private userService: UserService
  ) {}

    logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}