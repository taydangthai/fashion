import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Supplier } from '../models/supplier';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  private readonly API_URL = 'http://localhost:8000/api/auth/supplier';

  constructor(private http: HttpClient) { }

  getListSupplier(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(`${this.API_URL}`);
  }
  getSupplierById(id: number): Observable<Supplier> {
    return this.http.get<Supplier>(`${this.API_URL}/${id}`);
  }
  postSupplier(supplier: Supplier): Observable<Supplier> {
    return this.http.post<Supplier>(this.API_URL, supplier);
  }
  deleteSupplier(id: number): Observable<any> {
    return this.http.delete(`${this.API_URL}/${id}`);
  }
  putSupplier(supplier: Supplier): Observable<Supplier> {
    return this.http.put<Supplier>(`${this.API_URL}/${supplier.supplierId}`, supplier);
  }
}
