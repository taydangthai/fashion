import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Order} from '../models/order';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private url = 'http://localhost:8000/api/auth/order';

  constructor(private http: HttpClient) {
  }

  getOrderList(): Observable<Order[]> {
    return this.http.get<Order[]>(this.url);
  }

  getCart(id: string): Observable<Order> {
    return this.http.get<Order>(this.url + '/cart/' + id);
  }

  getItem(id: number): Observable<any> {
    return this.http.get<any>(this.url + '/' + id);
  }

  findIdOrderItemListByUserId(id: number): Observable<any> {
    return this.http.get<any>(this.url + '/cart/order-item/' + id);
  }

  createItem(order: Order): Observable<any> {
    return this.http.post(this.url, order);
    console.log(order)
  }

  editItem(order): Observable<any> {
    console.log(order);
    return this.http.put(this.url + '/' + order.id, order);
  }

  toOrder(order): Observable<any> {
    console.log(order);
    return this.http.put(this.url + '/toOrder', order);
  }

  addOrderItemToCart(idOrder: number, orderItem): Observable<any> {
    return this.http.put(this.url + '/add/' + idOrder, orderItem);
  }

  deleteItem(id: number): Observable<any> {
    return this.http.delete(this.url + '/' + id);
  }

  changeOrderStatus(id, status): Observable<any> {
    return this.http.put(this.url + '/change-status/' + id, status);
  }

  findByStatusAndUserId(id, status): Observable<any> {
    return this.http.put(this.url + '/change-status/' + id, status);
  }

  findAllByUserId(id: string): Observable<any> {
    return this.http.get<any>(this.url + '/user/' + id);
  }
  deleteOrder(id: number): Observable<any> {
    return this.http.delete<any>(this.url + '/' + id);
  }
}
