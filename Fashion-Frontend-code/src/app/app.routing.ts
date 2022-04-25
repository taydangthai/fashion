import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from './base-admin/layouts/admin-layout/admin-layout.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { UserLayoutComponent } from './base-user/layouts/user-layout/user-layout.component';
import { GuardService } from './auth/service-guard/guard.service';
import { UserGuardService} from './auth/service-guard/user-guard.service';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full',
  }/*,
  {
    path: '**',
    redirectTo: 'login'
  }*/
  ,
  {
    path: 'admin',
    component: AdminLayoutComponent,
    canActivate: [GuardService],
    children: [{
        path: '',
        loadChildren: './base-admin/layouts/admin-layout/admin-layout.module#AdminLayoutModule',
    }]
  },
  {
    path: 'home',
    component: UserLayoutComponent,
    canActivate: [UserGuardService],
    children: [
      {
        path: '',
        loadChildren: './base-user/layouts/user-layout/user-layout.module#UserLayoutModule'
      }]},
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
})
export class AppRoutingModule { }
