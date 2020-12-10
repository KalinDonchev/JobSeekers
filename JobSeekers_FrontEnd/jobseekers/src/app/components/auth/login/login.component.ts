import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ILoginUser } from 'src/app/core/interfaces/login-user';
import { AuthService } from 'src/app/core/services/auth.service';
import { TokenStorageService } from 'src/app/core/services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  hide: boolean;
  errorMessage = '';
  private loginUser: ILoginUser;


  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,private router: Router) {
    this.hide = true;
   }

  ngOnInit(): void {
  }

  loginHandler(value: {username: string, password: string}): void{
    
    this.loginUser = {
      username: value.username,
      password: value.password
    }

    this.authService.login(this.loginUser).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);
        this.tokenStorage.saveUser(this.loginUser);
        this.router.navigate(['/home']);
      },
      error => {
        this.errorMessage = error.error.message;
      }
    );
  }

}
