import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {TokenStorageService} from '../service-auth/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class GuardService implements CanActivate {

  constructor(private router: Router,
              private token: TokenStorageService) {
  }

  // tslint:disable-next-line:max-line-length
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if (this.token.getToken()) {
      for (const role of this.token.getAuthorities()) {
        if (role === 'ROLE_ADMIN' || role === 'ROLE_PM') {
          return true;
        }
      }
      this.router.navigate(['/home']);
      return false;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}
