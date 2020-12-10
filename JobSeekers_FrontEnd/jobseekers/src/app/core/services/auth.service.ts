import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { IUser } from '../interfaces/user';
import { BehaviorSubject, Observable } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { IJwtResponse } from '../interfaces/jwt-response';
import { TokenStorageService } from './token-storage.service';
import { ILoginUser } from '../interfaces/login-user';
import { UserService } from './user.service';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
}

const apiBaseUrl = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = apiBaseUrl + '/api/auth/login';
  private signupUrl = apiBaseUrl + '/api/auth/register';
  
  constructor(private http: HttpClient, private tokenService: TokenStorageService, private userService: UserService) {
  }

  getCurrentUserUsername(): string {
    return this.tokenService.getUsername();
  }
   

  login(credentials: ILoginUser): Observable<IJwtResponse> {
    return this.http.post<IJwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  register(data: IUser): Observable<any> {
    return this.http.post(this.signupUrl, data, httpOptions);
  }

  isAuthenticated(): boolean {
    return this.tokenService.getToken() !== undefined && this.tokenService.getToken() !== null;
  }

  logout(): void {
    this.tokenService.signOut();
  }



}
