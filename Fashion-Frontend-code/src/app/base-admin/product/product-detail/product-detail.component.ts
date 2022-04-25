import { Component, OnInit } from '@angular/core';
import {Subscription} from 'rxjs';
import {ProductDetail} from '../../../models/productDetail';
import {ProductDetailService} from '../../../services/product-detail.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  subscription: Subscription;
  productDetailClass: ProductDetail;

  constructor(private productDetailService: ProductDetailService) { }

  ngOnInit(): void {
  }

  getDetailByProductId(id: number) {
    this.subscription = this.productDetailService.getProductDetailById(id).subscribe(
      data => {
        this.productDetailClass = data;
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }

}
