import {Component, Input, OnInit, Output} from '@angular/core';
import {OrderService} from '../../../services/order.service';
import {TokenStorageService} from '../../../auth/service-auth/token-storage.service';
import {Router} from '@angular/router';
import {ProductDetailService} from '../../../services/product-detail.service';
import { EventEmitter } from '@angular/core';
import {Order} from '../../../models/order';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-order-action',
  templateUrl: './order-action.component.html',
  styleUrls: ['./order-action.component.css']
})
export class OrderActionComponent implements OnInit {

  private subscription: Subscription;
  private orderList: Order[];
  // orderList: Order[];

  constructor(private orderService: OrderService,
              private productDetailService: ProductDetailService,
              private token: TokenStorageService,
              private router: Router) {
  }

  ngOnInit() {
    this.listOrder();
  }
  listOrder() {
    this.subscription = this.orderService.getOrderList().subscribe(
      data => {
        this.orderList = data;
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }
  changeToProcessing(idOrder) {
    this.orderService.changeOrderStatus(idOrder, 'processing').subscribe(next => {
      this.ngOnInit();
    });
  }
  changeToDone(idOrder) {
    this.orderService.changeOrderStatus(idOrder, 'Done').subscribe(next => {
      this.ngOnInit();
    });
  }
  deleteOrder(id: number) {
    const choice = confirm('Are you sure to remove this order ?');
    if (choice) {
      this.orderService.deleteOrder(id)
        .subscribe(
          data => {
            console.log(data);
            this.ngOnInit();
          },
          error => {
            console.log(error);
            this.ngOnInit();
          }
        );
    }
  }
}
