import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private baseUrl = 'http://localhost:8000/api/auth/admin/product';
  private url = 'http://localhost:8000/api/auth/product';

  constructor(private http: HttpClient) { }

  getListProduct(): Observable<any> {
    return this.http.get<Product[]>(`${this.url}`);
  }
  getProduct(productId: number): Observable<any> {
    return this.http.get(`${this.url}/${productId}`);
  }
  getPageProduct(page: number): Observable<any> {
    return this.http.get<Product[]>(`${this.url}/page/${page}`);
  }
  deleteProduct(productId): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${productId}`);
  }
  putProduct(product: Product): Observable<any> {
    return this.http.put(`${this.baseUrl}/${product.productId}`,
    {
      name: product.name,
      price: product.price,
      description: product.description,
      quantity: product.quantity,
      pictures: product.picture,
      category: product.category,
      supplier: product.supplier
    });
  }
  postProduct(product: Product): Observable<any> {
    return this.http.post(this.baseUrl,
    {
      name: product.name,
      price: product.price,
      description: product.description,
      quantity: product.quantity,
      pictures: product.picture,
      category: product.category,
      supplier: product.supplier
    });
  }
}
