import {CartItem} from './cart';

export class ShoppingCart {
  public items: CartItem[] = new Array<CartItem>();
  public id: number;
  public grossTotal = 0;
  public deliveryTotal = 0;
  public itemsTotal = 0;
  public quantityInCart: any[];
}
