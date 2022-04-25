import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Size} from '../models/size';

@Injectable({
  providedIn: 'root'
})
export class SizeService {

  private baseUrl = 'http://localhost:8000/api/auth/size';
  constructor(private http: HttpClient) { }
  getSize(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
  getSizeList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
  // tslint:disable-next-line:ban-types
  postSize(size: Object): Observable<Size> {
    return this.http.post<Size>(`${this.baseUrl}`, size);
  }
  deleteSize(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, {responseType: 'text'});
  }
  putSize(size: Size): Observable<object> {
    return this.http.put(`${this.baseUrl}/${size.sizeId}`, size);
  }
}
