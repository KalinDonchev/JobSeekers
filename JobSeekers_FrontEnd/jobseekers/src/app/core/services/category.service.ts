import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ICategoryInfo } from '../interfaces/category-info';

const baseURL = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private getUrl = `${baseURL}/api/category/all`;

  constructor(private http: HttpClient) { }

  getAllCategories(): Observable<ICategoryInfo[]> {
    return this.http.get<ICategoryInfo[]>(this.getUrl);
  }

}
