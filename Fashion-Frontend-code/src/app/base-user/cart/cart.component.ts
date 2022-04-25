import { Component, OnInit } from '@angular/core';
import {Product} from '../../models/product';
import {PromoCodeService} from './promo-code.service';
import {CartProductService} from './cart-product.service';
import {Router, RouterModule} from '@angular/router';
import {PaymentService} from '../../services/payment.service';
import {Payment} from '../../models/payment';
import {PictureService} from '../../services/picture.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {User} from '../../models/user';
import {ProductDetail} from '../../models/productDetail';
import {formatDate} from '@angular/common';
import next from 'ajv/dist/vocabularies/next';
import {OrderService} from '../../services/order.service';
import {Order} from '../../models/order';
import {TokenStorageService} from '../../auth/service-auth/token-storage.service';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  constructor(private productService: CartProductService,
              private token: TokenStorageService,
              private promoCodeService: PromoCodeService,
              private route: Router,
              private paymentService: PaymentService,
              private fb: FormBuilder,
              private orderService: OrderService,
              private userService: UserService
              ) {

  }
  activeNum=0;
  numberItem:number ;
  subtotal:number;
  total:number;
  tax:number;
  discountPercent: number =0;
  discount: number=0 ;
  taxPercent: number = 10;
  products: Product[];
  detailProduct: ProductDetail[];
  payment;
  paymentOder: Payment={};
  orderForm: FormGroup;
  order: Order;
  myDate= Date.now();
  user: User;
  ngOnInit(): void {
    this.products=this.productService.getProducts();
    this.detailProduct=this.productService.getDetails();
    this.user=this.token.getUser();
    this.ngDocheck();
    this.paymentService.getPayment().subscribe(payment=>{
      console.log(payment);
      this.payment = payment;
    });

    console.log(this.orderForm.value)
  }
  ngDocheck() {
    this.numberItem = 0;
    this.subtotal = 0;
    for (const product of this.products) {
      this.numberItem += product.quantity;
      this.subtotal += product.price * product.quantity;
    }
    this.discount = (this.subtotal * this.discountPercent) / 100;
    this.tax = ((this.subtotal - this.discount) * this.taxPercent) / 100;
    this.total=this.subtotal+this.tax-this.discount;
    this.orderForm= this.fb.group({
      orderId: '',

      productDetails: this.detailProduct,
      phoneOrder: '',
      payment: this.paymentOder,
      deliveryAddress: '',
      total: this.total,
      dateTime: this.myDate,
      status: 'normal',
    });
    console.log(this.paymentOder)
  }
  removeProduct(productId: number){
    this.productService.removeProduct(productId);
    this.ngDocheck();

  }
  updateQuantity(p:{productId:number,quantity:number})
  {
    this.productService.updateQuantity(p.productId,p.quantity);
    this.ngDocheck();
  }
  handleApplyPromo(code:string){
    this.discountPercent=this.promoCodeService.applyPromoCode(code);
    this.discount=(this.subtotal*this.discountPercent) /100;
    if (this.discount>0){
      alert('promo code was applied');
    } else {
      alert(' sorry wrong promocode!')
    }
    this.ngDocheck();
  }
  checkOut(){
  this.activeNum=1;
  }


  onSubmit(){
    this.order=this.orderForm.value;
    this.orderService.createItem(this.orderForm.value).subscribe( next => {
      this.ngOnInit();
      alert('Đặt hàng thành công!');

    });
    // console.log(this.orderForm.value)
  }
  onChange(id)
  {
    this.paymentService.getPaymentById(id).subscribe(next=>{
      this.paymentOder = next;
      console.log(this.paymentOder)
    });
  }
}
