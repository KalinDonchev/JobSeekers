import { Component, OnInit } from '@angular/core';
import { IUser } from 'src/app/core/interfaces/user';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  private user: IUser;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  registerHandler(value: { username: string, email: string, password: string, phoneNumber: string }): void {
    this.user = {
      _id: null,
      username: value.username,
      email: value.email,
      password: value.password,
      phoneNumber: value.phoneNumber
    }
  }

}
