import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ICreateOffer } from '../interfaces/create-offer';
import { IOfferDetails } from '../interfaces/offer-detail';
import { IOfferInfoList } from '../interfaces/offer-info-list';

const baseURL = 'http://localhost:8080/api/offers';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  private createUrl = baseURL + '/create';
  private getAllUrl = baseURL + '/all';

  constructor(private http: HttpClient) { }


  createOffer(data: any): Observable<any> {
    console.log(data);
    return this.http.post<any>(this.createUrl, data, httpOptions);
  }

  getAllOffers(): Observable<IOfferInfoList[]> {
    return this.http.get<IOfferInfoList[]>(this.getAllUrl);
  }

  loadOffer(id: string): Observable<IOfferDetails> {
    return this.http.get<IOfferDetails>(`${baseURL}/details/${id}`);
  }

  deleteOffer(params: any) {
    return this.http.delete<any>(`${baseURL}/delete`, { params })
  }

  getAllOffersByCreator(username: string): Observable<IOfferInfoList[]>{
    return this.http.get<IOfferInfoList[]>(`${baseURL}/created-by/${username}`);
  }
  getAllFavOffersByUser(username: string): Observable<IOfferInfoList[]>{
    return this.http.get<IOfferInfoList[]>(`${baseURL}/favourite-offers/${username}`);
  }

  getLatestOffers(limit: number) {
    return this.http.get<IOfferInfoList[]>(`${this.getAllUrl}?limit=${limit}`);
  }




}
