import {Routes} from '@angular/router';
import {UserProductComponent} from '../../user-product/user-product.component';
import {HomeComponent} from '../../home/home.component';
import {CartComponent} from '../../cart/cart.component';



export const UserLayoutRoutes: Routes = [
  { path: 'trangchu',  component: HomeComponent },
  { path: 'sanpham',  component: UserProductComponent },
  { path: 'giohang',  component: CartComponent },
];
