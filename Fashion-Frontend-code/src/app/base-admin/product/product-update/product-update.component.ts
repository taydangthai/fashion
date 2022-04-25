import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Product} from '../../../models/product';
import {Supplier} from '../../../models/supplier';
import {Category} from '../../../models/category';
import {CategoryService} from '../../../services/category.service';
import {SupplierService} from '../../../services/supplier.service';
import {PictureService} from '../../../services/picture.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../../../services/product.service';
import {TokenStorageService} from '../../../auth/service-auth/token-storage.service';
import {Picture} from '../../../models/Picture';

@Component({
  selector: 'app-product-update',
  templateUrl: './product-update.component.html',
  styleUrls: ['./product-update.component.css']
})
export class ProductUpdateComponent implements OnInit {

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
  constructor(
              private categoryService: CategoryService,
              private supplierService: SupplierService,
              private pictureService: PictureService,
              private fb: FormBuilder,
              private router: Router,
              private productService: ProductService,
              private token: TokenStorageService,
              private route: ActivatedRoute) { }

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
  getpiccture(id: number) {
    this.pictureService.getListPicture1(id).subscribe(data => {
      this.listpicture = data;
      console.log(data);
    });
  }
  onSubmit() {
    if (this.productForm.valid) {
      console.log(this.productForm);
      const {value} = this.productForm;
      this.product = value;
      for (const preview of this.previewUrl) {
        this.productService.putProduct(this.product);
      }
    } else {
      console.log('error');
    }
    setTimeout(() => {
      this.updateProduct();
    }, 200);
  }
  updateProduct() {
    this.product.picture = this.picture;
    this.product.category = this.category;
    this.product.supplier = this.supplier;
    this.productService.putProduct(this.product).subscribe(next => {
      this.ngOnInit();
      alert('Update is succeed!');
      this.router.navigate(['admin', 'home']);
    });
  }
  onSelectFile(event) {
    this.useFile = [];
    this.useFile = event.srcElement.files;
    console.log(this.useFile);
    this.preview();
  }
  preview() {
    // Show preview
    for (let i = 0; i < this.useFile.length; i++) {
      const mimeType = this.useFile[i].type;
      if (mimeType.match(/image\/*/) == null) {
        return;
      }

      const reader = new FileReader();
      reader.readAsDataURL(this.useFile[i]);
      reader.onload = event => {
        if (typeof reader.result === 'string') {
          this.previewUrl[i] = reader.result;
        }
      };
    }
    console.log(this.previewUrl);
  }
  addCategory(id) {
    this.categoryService.getCategoryById(id).subscribe(next => this.category = next);
  }
  addSupplier(id) {
    this.supplierService.getSupplierById(id).subscribe(next => this.supplier = next);
  }
}
