import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtResponse } from '../model-auth/jwt-response';
import { AuthLoginInfo } from '../model-auth/login-infor';
import { SignUpInfo } from '../model-auth/signup-infor';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = 'http://localhost:8000/api/auth/login';
  private registerUrl = 'http://localhost:8000/api/auth/signUp';
  private changPassword = 'http://localhost:8000/api/auth/update-password';
  private updateProfile = 'http://localhost:8000/api/auth/update-profile';

  constructor(private http: HttpClient) { }

  loginAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  register(user: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.registerUrl, user, httpOptions);
  }
}
