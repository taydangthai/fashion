import {Product} from './product';

export interface Picture {
  pictureId?: number;
  fileName?: string;
  contentType?: string;
  url?: string;
  product?: Product[];
}
