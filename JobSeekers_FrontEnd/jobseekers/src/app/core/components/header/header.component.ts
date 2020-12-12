import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
 

  constructor(private authService: AuthService,private router: Router) { }

  get isLogged(): boolean {
    return this.authService.isAuthenticated();
  }

  get userUsername(): string {
    return this.authService.getCurrentUserUsername();
  }

  ngOnInit(): void {
  }

  logoutHandler(): void { 
    this.authService.logout()
    this.router.navigate(['/auth/login']);
  }


}
