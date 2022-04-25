import {Component, Input, OnInit} from '@angular/core';
import {ProductService} from '../../services/product.service';
import {TokenStorageService} from '../../auth/service-auth/token-storage.service';
import {CategoryService} from '../../services/category.service';
import {SupplierService} from '../../services/supplier.service';
import {PictureService} from '../../services/picture.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {Product} from '../../models/product';
import {Supplier} from '../../models/supplier';
import {Category} from '../../models/category';
import {Subscription} from 'rxjs/internal/Subscription';
import {CartProductService} from '../cart/cart-product.service';
import {ColorService} from '../../services/color.service';
import {SizeService} from '../../services/size.service';
import {ProductDetailService} from '../../services/product-detail.service';

@Component({
  selector: 'app-user-product',
  templateUrl: './user-product.component.html',
  styleUrls: ['./user-product.component.css']
})
export class UserProductComponent implements OnInit {
  product: Product[];
  category: any;
  supplier: any;
  picture: any[];
  name: any;
  productId: number;
  private subscription: Subscription;
  // page
  private page = 1;
  private totalPage: number;
  public productPage: Product[] = [];
  private listProductNotPage: Product[];
  private notification: string;
  pageItem = [];
  listColor = [];
  listSize = [];
  form: FormGroup;
  productAdd: Product;

  constructor(private productService: ProductService,
              private cartService: CartProductService,
              private token: TokenStorageService,
              private categoryService: CategoryService,
              private supplierService: SupplierService,
              private sizeService: SizeService,
              private pictureService: PictureService,
              private fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private colorService: ColorService,
              private productDetailService: ProductDetailService
              ) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      quantity: '',
      color: '',
      size: ''
    });
   this.categoryService.getListCategory().subscribe(data=>{
     this.category=data;
   })
    this.getListProduct();
    this.pageListProduct();
    this.getListColor();
    this.getListSize();
  }
  getListProduct() {
    this.productService.getListProduct().subscribe(data => {
      this.product = data;
      console.log(data);
    });
  }
  getListColor() {
    this.colorService.getColorList().subscribe(color => {
      this.listColor = color;
    });
  }
  getListSize() {
    this.sizeService.getSizeList().subscribe(size => {
      this.listSize = size;
    });
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
  public onOpenModal(product: Product, mode: string): void {
    console.log(product);
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      this.productAdd = product;
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    container.appendChild(button);
    button.click();
  }

  addTocart(){
    this.cartService.addToCart(this.productAdd,this.form)
  }

  onSubmit() {
    if (this.form.valid) {
      const {value} = this.form;

      console.log(value);
      // this.productDetailService.createProductDetail(value);

    }
  }
  findByCategory(categoryName){
    // const a = this.product.find(data => data.category.categoryName === categoryName );
    const a=this.product.filter(data=>data.category.categoryName === categoryName)
    console.log(a);
  }
}
