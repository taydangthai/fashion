import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {TokenStorageService} from '../service-auth/token-storage.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserGuardService implements CanActivate {

  constructor(private router: Router,
              private token: TokenStorageService) { }

  // tslint:disable-next-line:max-line-length
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if (this.token.getToken()) {
      for (const role of this.token.getAuthorities()) {
        if (role === 'ROLE_USER') {
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
