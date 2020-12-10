import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseURL = 'http://localhost:8080';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  private createUrl = baseURL + '/api/offers/create';

  constructor(private http: HttpClient) { }


  createOffer(data: any): Observable<any>{
    return this.http.post<any>(this.createUrl,data,httpOptions);
  }

}
