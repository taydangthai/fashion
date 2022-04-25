import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
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

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {

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

  constructor(public dialogRef: MatDialogRef<any>,
              @Inject(MAT_DIALOG_DATA) public message: string,
              private categoryService: CategoryService,
              private supplierService: SupplierService,
              private pictureService: PictureService,
              private fb: FormBuilder,
              private router: Router,
              private productService: ProductService,
              private token: TokenStorageService) { }

  ngOnInit(): void {
    this.productForm = this.fb.group({
      id: '',
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
  }
  onSelectFile(event) {
    this.useFile = [];
    this.useFile = event.srcElement.files;
    console.log(this.useFile);
    this.preview();
  }
  onSubmit() {
    if (this.productForm.valid) {
      const {value} = this.productForm;
      this.product = value;
      for (const preview of this.useFile) {
        this.pictureService.postPicture(preview).subscribe(
          next => {
            this.picture.push({
              pictureId: next
            });
          }
        );
      }
    } else {
      console.log('error');
    }
    setTimeout(() => {
      this.createProduct();
    }, 100);
  }
  createProduct() {
    this.product.picture = this.picture;
    this.product.category = this.category;
    this.product.supplier = this.supplier;
    this.productService.postProduct(this.product).subscribe(next => {
      this.ngOnInit();
      alert('Created a new product!');
      this.router.navigate(['admin', 'home']);
    });
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
