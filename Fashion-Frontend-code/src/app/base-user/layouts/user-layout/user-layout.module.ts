import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ChartsModule} from 'ng2-charts';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ToastrModule} from 'ngx-toastr';
import {UserLayoutRoutes} from './user-layout.routing';
import {UserProductComponent} from '../../user-product/user-product.component';
import {CartComponent} from '../../cart/cart.component';
import {HomeComponent} from '../../home/home.component';
import {CardProductComponent} from '../../user-product/card-product/card-product.component';
import {CartProductsComponent} from '../../cart/cart-products/cart-products.component';
import {CartProductPictureComponent} from '../../cart/cart-products/cart-product-picture/cart-product-picture.component';
import {CartPromotionComponent} from '../../cart/cart-promotion/cart-promotion.component';
import {CartSummaryComponent} from '../../cart/cart-summary/cart-summary.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(UserLayoutRoutes),
    FormsModule,
    ChartsModule,
    NgbModule,
    ToastrModule.forRoot(),
    ReactiveFormsModule,
    MatFormFieldModule,
    MatDialogModule,
    MatInputModule
  ],
    declarations: [
        UserProductComponent,
        CartComponent,
        HomeComponent,
        CardProductComponent,
        CartProductsComponent,
        CartProductPictureComponent,
        CartPromotionComponent,
        CartSummaryComponent
    ]
})

export class UserLayoutModule { }
