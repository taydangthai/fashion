import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {TokenStorageService} from '../../../auth/service-auth/token-storage.service';

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [
  { path: 'trangchu', title: 'Trang Chủ',  icon: 'design_app', class: '' },
  { path: 'sanpham', title: 'Sản Phẩm',  icon: 'design_app', class: '' },

];
@Component({
  selector: 'app-navbar-user',
  templateUrl: './navbar-user.component.html',
  styleUrls: ['./navbar-user.component.css']
})
export class NavbarUserComponent implements OnInit {

  menuItems: any[];
  constructor(private router: Router,
              private token: TokenStorageService) { }

  ngOnInit(): void {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }

  logout() {
    const choice = confirm('Are you sure to logout this page?');
    if (choice) {
      this.token.signOut();
      this.router.navigateByUrl('/login');
      // this.ngOnDestroy();
      this.ngOnInit();
      window.location.reload();
    }
  }
}
