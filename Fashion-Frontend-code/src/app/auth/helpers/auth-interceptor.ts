import { HttpEvent, HTTP_INTERCEPTORS} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../service-auth/token-storage.service';


const TOKEN_HEADER_KEY = 'Authorization';

// intercept()phương pháp kiểm tra và chuyển đổi các yêu cầu HTTP trước khi chúng được gửi đến máy chủ.
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private token: TokenStorageService) {
  }
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      let authReq = req;
      const token = this.token.getToken();
      if (token != null) {
        authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
      }
      return next.handle(authReq);
    }

}
export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
