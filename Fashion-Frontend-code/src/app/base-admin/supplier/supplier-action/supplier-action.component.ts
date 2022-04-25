import { Component, OnInit } from '@angular/core';
import {Subscription} from 'rxjs';
import {Supplier} from '../../../models/supplier';
import {Colors} from '../../../models/colors';
import {ColorService} from '../../../services/color.service';
import {SupplierService} from '../../../services/supplier.service';
import {NgForm} from '@angular/forms';
import {Category} from '../../../models/category';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-supplier-action',
  templateUrl: './supplier-action.component.html',
  styleUrls: ['./supplier-action.component.css']
})
export class SupplierActionComponent implements OnInit {
  private subscription: Subscription;
  private supplierClass: Supplier[];
  private editSupplier: Supplier;
  private deleteSupplier: Supplier;

  private colorsClass: Colors[];

  constructor(private supplierService: SupplierService,
              private colorsService: ColorService) { }

  ngOnInit(): void {
    this.listColor();
    this.listSupplier();
  }
  listSupplier() {
    this.subscription = this.supplierService.getListSupplier().subscribe(
      data => {
        this.supplierClass = data;
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }
  onAddSupplier(addFormS: NgForm): void {
    document.getElementById('add-supplier-form').click();
    this.supplierService.postSupplier(addFormS.value).subscribe(
      (response: Supplier) => {
        console.log(response);
        alert('Thêm Thành Công');
        this.listSupplier();
        addFormS.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addFormS.reset();
      }
    );
  }
  onUpdateSupplier(category: Category): void {
    document.getElementById('edit-category-form').click();
    this.supplierService.putSupplier(category).subscribe(
      (response: Supplier) => {
        console.log(response);
        this.listSupplier();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  onDeleteSupplier(categoryId: number): void {
    this.supplierService.deleteSupplier(categoryId).subscribe(
      (response: void) => {
        console.log(response);
        this.listSupplier();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public onOpenModal(supplier: Supplier, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addEmployeeModal');
    }
    if (mode === 'edit') {
      this.editSupplier = supplier;
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if (mode === 'delete') {
      this.deleteSupplier = supplier;
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    container.appendChild(button);
    button.click();
  }

  listColor() {
    this.subscription = this.colorsService.getColorList().subscribe(
      data => {
        this.colorsClass = data;
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }
}
