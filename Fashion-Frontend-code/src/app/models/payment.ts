import {Order} from './order';

export interface Payment {
  paymentId?: number;
  paymentName?: string;
  order?: Order;
}
