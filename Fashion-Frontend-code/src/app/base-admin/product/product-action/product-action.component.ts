import { Component, OnInit } from '@angular/core';
import {Product} from '../../../models/product';
import {ProductService} from '../../../services/product.service';
import {TokenStorageService} from '../../../auth/service-auth/token-storage.service';
import {CategoryService} from '../../../services/category.service';
import { Subscription } from 'rxjs/internal/Subscription';
import { FormBuilder } from '@angular/forms';
import {Router} from '@angular/router';
import {SupplierService} from '../../../services/supplier.service';
import {PictureService} from '../../../services/picture.service';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {ProductCreateComponent} from '../product-create/product-create.component';

@Component({
  selector: 'app-product-action',
  templateUrl: './product-action.component.html',
  styleUrls: ['./product-action.component.css']
})
export class ProductActionComponent implements OnInit {

  private subscription: Subscription;
  // page
  private page = 1;
  private totalPage: number;
  public productPage: Product[] = [];
  private listProductNotPage: Product[];
  private notification: string;
  pageItem = [];

  constructor(private productService: ProductService,
              private token: TokenStorageService,
              private categoryService: CategoryService,
              private supplierService: SupplierService,
              private pictureService: PictureService,
              private fb: FormBuilder,
              private router: Router,
              public dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.pageListProduct();
    // this.subscription = this.productService.getListProduct().subscribe(
    //   (data: Product[]) => {
    //     this.listProductNotPage = data;
    //     // tslint:disable-next-line:triple-equals
    //     if ((this.listProductNotPage.length % 6) != 0) {
    //       this.totalPage = (Math.round(this.listProductNotPage.length / 6)) + 1;
    //     }
    //   }
    // );
  }
  pageListProduct() {
    this.subscription = this.productService.getPageProduct(this.page).subscribe(
      data => {
        this.productPage = data.product;
        this.totalPage = data.totalPage;
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
    this.pageItem = Array(length = this.totalPage );
  }
  setPage(pages: number) {
    this.page = pages;
    this.pageListProduct();
  }
  firstPage() {
    this.page = 1;
    this.pageListProduct();
  }
  previousPage() {
    if (this.page <= 1) {
      this.page = 1;
    } else {
      this.page = this.page - 1;
    }
    this.pageListProduct();
  }
  nextPage() {
    if (this.page >= this.totalPage) {
      this.page = this.totalPage;
    } else {
      this.page = this.page + 1;
    }
    this.pageListProduct();
  }
  lastPage() {
    this.page = this.totalPage;
    this.pageListProduct();
  }
  deleteProduct(productId) {
    if (this.token.getToken()) {
      for (const role of this.token.getAuthorities()) {
        if (role === 'ROLE_ADMIN') { // role === 'ROLE_ADMIN' || role === 'ROLE_PM' || role === 'ROLE_USER'
          const choice = confirm('Bạn có chắc chắn muốn xoá sản phẩm?');
          if (choice) {
            this.productService.deleteProduct(productId).subscribe(
              response => {
                console.log(response);
                this.notification = `xoá sản phẩm ${productId} thành công`;
                console.log(this.notification);
                alert(`xoá sản phẩm ${productId} thành công`);
                this.firstPage();
                this.router.navigate(['admin', 'product']);
              });
          }
        }
      }
    }
  }
  openDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.height = '80%';
    this.dialog.open(ProductCreateComponent, dialogConfig);
  }

  editProduct(productId: number) {
    console.log('edit product');
    this.router.navigate(['admin', 'product-update', productId]);
    // const dialogConfig = new MatDialogConfig();
    // dialogConfig.disableClose = true;
    // dialogConfig.autoFocus = true;
    // dialogConfig.width = '30%';
    // dialogConfig.height = '90%';
    // this.dialog.open(ProductUpdateComponent, dialogConfig);
  }
  detailProduct(productId: number) {
    console.log('edit product');
    this.router.navigate(['admin', 'product-detail', productId]);
    // const dialogConfig = new MatDialogConfig();
    // dialogConfig.disableClose = true;
    // dialogConfig.autoFocus = true;
    // dialogConfig.width = '30%';
    // dialogConfig.height = '90%';
    // this.dialog.open(ProductUpdateComponent, dialogConfig);
  }
}
