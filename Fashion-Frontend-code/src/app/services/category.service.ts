import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private readonly API_URL = 'http://localhost:8000/api/auth/category';

  constructor(private http: HttpClient) { }

  getListCategory(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.API_URL}`);
  }
  getCategoryById(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.API_URL}/${id}`);
  }
  postCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(this.API_URL, category);
  }
  deleteCategory(id: number): Observable<any> {
    return this.http.delete(`${this.API_URL}/${id}`);
  }
  putCategory(category: Category): Observable<Category> {
    return this.http.put<Category>(`${this.API_URL}/${category.categoryId}`, category);
  }
}
