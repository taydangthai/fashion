import {User} from './user';
import {ProductDetail} from './productDetail';
import {Payment} from './payment';

export class Order {
  orderId?: number;
  user?: User;
  productDetails?: ProductDetail[];
  phoneOrder?: string;
  payment?: Payment;
  deliveryAddress?: string;
  total?: number;
  dateTime?: Date;
  status?: string;
}
