import { Injectable } from '@angular/core';
import {Product} from '../../models/product';
import {ProductDetail} from '../../models/productDetail';

@Injectable({
  providedIn: 'root'
})
export class CartProductService {

  products: Product[] = JSON.parse(localStorage.getItem('products'));
  detailProduct: ProductDetail[] = JSON.parse(localStorage.getItem('detail'));

  getProducts(): Product[] {
    return this.products;
  }
  getDetails(): ProductDetail[]{
    return this.detailProduct;
  }
  findById(id: number): Product  {
    return this.products.find(product => product.productId === id)!;

  }

  findIndexById(id: number): number {
    return this.products.findIndex(product => product.productId === id);
  }

  updateQuantity(id: number, quantity: number) {
    console.log(id);
    const product = this.findById(id);
    console.log(product);
    if (product) {
      product.quantity = quantity || 0;
    }
    localStorage.setItem("products",JSON.stringify(this.products));
  }

  removeProduct(id: number): boolean {
    const index = this.findIndexById(id);

    if (index !== -1) {
      this.products.splice(index, 1);
      localStorage.setItem("products",JSON.stringify(this.products));
      return true;
    }

    return false;
  }
  addToCart(productItem: Product, detail){
    productItem.quantity=parseInt(detail.value.quantity);
    console.log(detail.value);
    const  detailItem: ProductDetail = {
      product: productItem,
      size: detail.value.size,
      color: detail.value.color,
      quantity : detail.value.quantity
    }
    this.detailProduct.push(detailItem);
    console.log(detailItem);
    this.products.push(productItem);
    localStorage.setItem("products",JSON.stringify(this.products));
    localStorage.setItem("detail",JSON.stringify(this.detailProduct));

  }
  clearCart(){
    this.products = [];
    this.detailProduct = [];
    localStorage.setItem("products",JSON.stringify(this.products));
    localStorage.setItem("detail",JSON.stringify(this.detailProduct));
  }
}
