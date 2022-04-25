import { Component, OnInit } from '@angular/core';
import {Subscription} from 'rxjs';
import {ProductDetailService} from '../../../services/product-detail.service';
import {ProductDetail} from '../../../models/productDetail';
import {Supplier} from '../../../models/supplier';
import {Category} from '../../../models/category';
import {CategoryService} from '../../../services/category.service';
import {SupplierService} from '../../../services/supplier.service';
import {PictureService} from '../../../services/picture.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../../../services/product.service';
import {TokenStorageService} from '../../../auth/service-auth/token-storage.service';
import {Product} from '../../../models/product';

@Component({
  selector: 'app-product-detail-action',
  templateUrl: './product-detail-action.component.html',
  styleUrls: ['./product-detail-action.component.css']
})
export class ProductDetailActionComponent implements OnInit {

  subscription: Subscription;
  productDetailClass: ProductDetail;
  productForm: FormGroup;
  product: Product;
  category: any;
  supplier: any;
  previewUrl: any[];
  useFile: any[];
  picture: any[];
  price: any;
  name: any;
  quantity: any;
  supplierList: Supplier[];
  categoryList: Category[];
  listpicture = [];
  constructor(private productDetailService: ProductDetailService,
              private categoryService: CategoryService,
              private supplierService: SupplierService,
              private pictureService: PictureService,
              private fb: FormBuilder,
              private router: Router,
              private productService: ProductService,
              private token: TokenStorageService,
              private route: ActivatedRoute
              ) { }

  ngOnInit(): void {
    this.productForm = this.fb.group({
      productId: '',
      name: ['', [Validators.required, Validators.minLength(1)]],
      price: ['', [Validators.required, Validators.min(0)]],
      description: ['', [Validators.required]],
      quantity: ['', [Validators.required, Validators.min(0)]],
    });
    this.supplierService.getListSupplier().subscribe(next => this.supplierList = next);
    this.categoryService.getListCategory().subscribe(next => this.categoryList = next);
    this.useFile = [];
    this.previewUrl = [];
    this.picture = [];
    this.category = [];
    this.supplier = [];
    const productId = +this.route.snapshot.paramMap.get('productId');
    this.productService.getProduct(productId).subscribe(
      next => {
        this.productForm.patchValue(next);
        this.supplier = next.supplier;
        this.category = next.category;
        for (const picture of next.pictures) {
          this.previewUrl.push(picture.url);
        }
      }, error => {
        console.log(error);
        this.product = null;
      }
    );
    this.getpiccture(productId);
  }

  getDetailByProductId(id: number) {
    this.subscription = this.productDetailService.getProductDetailById(id).subscribe(
      data => {
        this.productDetailClass = data;
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }
  getpiccture(id: number) {
    this.pictureService.getListPicture1(id).subscribe(data => {
      this.listpicture = data;
      console.log(data);
    });
  }
  addCategory(id) {
    this.categoryService.getCategoryById(id).subscribe(next => this.category = next);
  }
  addSupplier(id) {
    this.supplierService.getSupplierById(id).subscribe(next => this.supplier = next);
  }
}
