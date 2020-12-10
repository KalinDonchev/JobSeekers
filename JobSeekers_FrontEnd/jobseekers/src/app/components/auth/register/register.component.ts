import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from 'src/app/core/interfaces/user';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  hide: boolean;
  hideConfirm: boolean;
  errorMessage = '';
  private user: IUser;

  constructor(private authService: AuthService,private router: Router) {
    this.hide = true;
    this.hideConfirm = true;
   }

  ngOnInit(): void {
  }

  registerHandler(value: { username: string, email: string, password: string,confirmPassword: string, phoneNumber: string }): void {
    this.user = {
      id: null,
      username: value.username,
      email: value.email,
      password: value.password,
      phoneNumber: value.phoneNumber
    }

    this.authService.register(this.user).subscribe((data) => {
      console.log(data);
      this.router.navigate(['/auth/login']);
    },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
    }
    )
    

  }

}
