import { Routes } from '@angular/router';
import { UserProfileComponent } from '../../user-profile/user-profile.component';
import { IconsComponent } from '../../icons/icons.component';
import { ProductActionComponent} from '../../product/product-action/product-action.component';
import { ProductDeleteComponent} from '../../product/product-delete/product-delete.component';
import { ProductUpdateComponent} from '../../product/product-update/product-update.component';
import { ProductCreateComponent} from '../../product/product-create/product-create.component';
import { CategoryActionComponent} from '../../category/category-action/category-action.component';
import { SupplierActionComponent} from '../../supplier/supplier-action/supplier-action.component';
import { UserActionComponent} from '../../user/user-action/user-action.component';
import {OrderActionComponent} from '../../order/order-action/order-action.component';
import {ProductDetailActionComponent} from '../../product-detail/product-detail-action/product-detail-action.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'home',             component: ProductActionComponent},
    { path: 'product-create',   component: ProductCreateComponent},
    { path: 'product-delete',   component: ProductDeleteComponent},
    { path: 'product-update/:productId',   component: ProductUpdateComponent},
    { path: 'order',            component: OrderActionComponent},
    { path: 'product-detail/:productId',            component: ProductDetailActionComponent},
    { path: 'category',         component: CategoryActionComponent},
    { path: 'supplier',         component: SupplierActionComponent},
    { path: 'user',             component: UserActionComponent},
    { path: 'profile',          component: UserProfileComponent },
    { path: 'icons',            component: IconsComponent }
];
