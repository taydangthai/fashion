import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../../../../models/product';
import {PictureService} from '../../../../services/picture.service';
import {ProductService} from '../../../../services/product.service';
import {TokenStorageService} from '../../../../auth/service-auth/token-storage.service';
import {CategoryService} from '../../../../services/category.service';
import {SupplierService} from '../../../../services/supplier.service';
import {FormBuilder} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-table-product',
  templateUrl: './table-product.component.html',
  styleUrls: ['./table-product.component.css']
})
export class TableProductComponent implements OnInit {

  @Input() product: Product;
  @Input() productId: number;
  listpicture = [];
  constructor(  private pictureService: PictureService,
                private productService: ProductService,
                private token: TokenStorageService,
                private categoryService: CategoryService,
                private supplierService: SupplierService,
                private fb: FormBuilder,
                private router: Router,
  ) { }

  ngOnInit(): void {
    this.getpiccture(this.productId);
  }
  getpiccture(id: number) {
    console.log(id);
    this.listpicture = [];
    this.pictureService.getListPicture1(id).subscribe(data => {
      this.listpicture = data;
      console.log(this.listpicture);
    });
  }

}
