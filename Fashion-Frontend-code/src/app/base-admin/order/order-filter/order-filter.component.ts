import { Component, OnInit } from '@angular/core';
import {OrderService} from '../../../services/order.service';
import {TokenStorageService} from '../../../auth/service-auth/token-storage.service';
import {map} from 'rxjs/operators';
import {Order} from '../../../models/order';

@Component({
  selector: 'app-order-filter',
  templateUrl: './order-filter.component.html',
  styleUrls: ['./order-filter.component.css']
})
export class OrderFilterComponent implements OnInit {

  orderListOrder: Order[];
  orderListProcessing: Order[];
  orderListDone: Order[];
  orderListCancel: Order[];

  constructor(private orderService: OrderService,
              private token: TokenStorageService) { }

  ngOnInit(): void {
    this.updateList();
  }
  updateList() {
    console.log('load');
    this.orderService.getOrderList().pipe(
      map(res => res.filter((order, i) => order.status === 'order'))
    ).subscribe(orderListOrder => {
      this.orderListOrder = orderListOrder;
    });
    this.orderService.getOrderList().pipe(
      map(res => res.filter((order, i) => order.status === 'processing'))
    ).subscribe(orderListProcessing => {
      this.orderListProcessing = orderListProcessing;
    });
    this.orderService.getOrderList().pipe(
      map(res => res.filter((order, i) => order.status === 'Done'))
    ).subscribe(orderListDone => {
      this.orderListDone = orderListDone;
    });
    this.orderService.getOrderList().pipe(
      map(res => res.filter((order, i) => order.status === 'Cancel'))
    ).subscribe(orderListCancel => {
      this.orderListCancel = orderListCancel;
    });
  }
}
