import {Product} from './product';

export interface Supplier {
  supplierId?: number;
  supplierName?: string;
  products?: Product;
}
