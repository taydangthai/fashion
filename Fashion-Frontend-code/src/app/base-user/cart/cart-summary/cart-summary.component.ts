import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-cart-summary',
  templateUrl: './cart-summary.component.html',
  styleUrls: ['./cart-summary.component.css']
})
export class CartSummaryComponent implements OnInit {

  @Input() sum: number;
  @Input() total: number ;
  @Input() tax: number ;
  @Input() discount: number ;

  constructor() { }

  ngOnInit(): void {
    console.log(this.sum)
  }

}
