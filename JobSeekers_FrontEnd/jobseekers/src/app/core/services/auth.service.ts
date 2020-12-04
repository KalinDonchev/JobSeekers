import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { IUser } from '../interfaces/user';
import { Observable } from 'rxjs';
import { IJwtResponse } from '../interfaces/jwt-response';

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

  constructor(private http: HttpClient) { }

  login(credentials: IUser): Observable<IJwtResponse> {
    return this.http.post<IJwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  register(data: IUser): Observable<any> {
    return this.http.post(this.signupUrl, data, httpOptions);
  }
  


}
