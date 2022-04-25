import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-cart-promotion',
  templateUrl: './cart-promotion.component.html',
  styleUrls: ['./cart-promotion.component.css']
})
export class CartPromotionComponent implements OnInit {
  promocde: string ='';
  @Output() onApplyPromoCode= new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }
  applyPromoCode(){
    const code = this.promocde;
    if (code && code.trim() !== '') {
      this.onApplyPromoCode.emit(code);
    }
  }
}
