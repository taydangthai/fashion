import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from '../../../models/product';

@Component({
  selector: 'app-cart-products',
  templateUrl: './cart-products.component.html',
  styleUrls: ['./cart-products.component.css']
})
export class CartProductsComponent implements OnInit {
  @Input() product: Product[]=[];
  @Input() numberItem: number;
  @Output() onRemoveProduct=new EventEmitter() ;
  @Output() onUpdateQuantity=new EventEmitter();
  constructor() { }

  ngOnInit(): void {
  }
  removeProduct(productId: number) {
    this.onRemoveProduct.emit(productId);
  }
  inputQuantity(productId: number,quantityElement: HTMLInputElement){

    const value = quantityElement.value;
    const intValue = parseInt(value);

    if (!intValue ) {
      quantityElement.value = -intValue + '';

    } else if (value.length > 2) {
      quantityElement.value = value.slice(0, 2);
    }

    this.onUpdateQuantity.emit({productId , quantity: parseInt(quantityElement.value) || ''});
    console.log(productId);
  }
}
