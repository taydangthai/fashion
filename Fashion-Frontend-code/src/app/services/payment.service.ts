import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient) { }
  private ulr = 'http://localhost:8000/api/auth/payment';

  getPayment(){
    return this.http.get(this.ulr);
  }
  getPaymentById(id: number){
    return this.http.get(`${this.ulr}/${id}`);
  }
}
