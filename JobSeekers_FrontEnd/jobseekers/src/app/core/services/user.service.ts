import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';

const baseURL = 'http://localhost:8080/api/user/profile';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient,private tokenService: TokenStorageService) { }

  getUserDetails(): Observable<any> {
    return this.http.get<any>(`${baseURL}?username=${this.tokenService.getUsername()}`);
  }


}
