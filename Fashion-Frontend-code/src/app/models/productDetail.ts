
import { Size } from './size';
import {Product} from './product';
import {Colors} from './colors';
import {Payment} from './payment';

export interface ProductDetail {
  detailId?: number;
  size?: Size;
  color?: Colors;
  payment?: Payment;
  product?: Product;
  quantity?: number;
  order?: any;
}
