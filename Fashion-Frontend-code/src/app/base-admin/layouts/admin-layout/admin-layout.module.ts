import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { UserProfileComponent } from '../../user-profile/user-profile.component';
import { IconsComponent } from '../../icons/icons.component';
import { ChartsModule } from 'ng2-charts';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ToastrModule } from 'ngx-toastr';
import { MatFormFieldModule} from '@angular/material/form-field';
import { ProductActionComponent} from '../../product/product-action/product-action.component';
import { ProductDeleteComponent} from '../../product/product-delete/product-delete.component';
import { ProductUpdateComponent} from '../../product/product-update/product-update.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule} from '@angular/material/input';
import { MatOptionModule} from '@angular/material/core';
import { ProductCreateComponent} from '../../product/product-create/product-create.component';
import { CategoryActionComponent} from '../../category/category-action/category-action.component';
import { SupplierActionComponent} from '../../supplier/supplier-action/supplier-action.component';
import { UserActionComponent} from '../../user/user-action/user-action.component';
import { OrderActionComponent} from '../../order/order-action/order-action.component';
import {ProductDetailActionComponent} from '../../product-detail/product-detail-action/product-detail-action.component';
import {TableProductComponent} from '../../product/product-action/table-product/table-product.component';



@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    ChartsModule,
    NgbModule,
    ToastrModule.forRoot(),
    ReactiveFormsModule,
    MatFormFieldModule,
    MatDialogModule,
    MatInputModule,
    MatOptionModule,
  ],
    declarations: [
        UserProfileComponent,
        IconsComponent,
        ProductActionComponent,
        ProductCreateComponent,
        ProductDeleteComponent,
        ProductUpdateComponent,
        CategoryActionComponent,
        SupplierActionComponent,
        UserActionComponent,
        OrderActionComponent,
        ProductDetailActionComponent,
        TableProductComponent
    ],
  providers: [
  ]
})

export class AdminLayoutModule {}
