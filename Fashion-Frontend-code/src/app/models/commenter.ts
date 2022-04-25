import {Product} from './product';

export interface Commenter {
  commenterId?: number;
  content?: string;
  dateTime?: string;
  product?: Product;
}
