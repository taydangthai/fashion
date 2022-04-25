import { HttpClient } from '@angular/common/http';
import { OnDestroy } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormControl, Validators} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthLoginInfo } from '../model-auth/login-infor';
import { AuthService } from '../service-auth/auth.service';
import { TokenStorageService } from '../service-auth/token-storage.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  returnUrl: string;
  isLoggedIn = false;
  isLoginFailed = false;
  roles: string[] = [];
  loginForm = new FormGroup(
    {username: new FormControl('',
        [Validators.required, Validators.minLength(4)]),
      password: new FormControl('')
  });
  constructor(
              private http: HttpClient,
              private route: ActivatedRoute,
              private router: Router,
              private authService: AuthService,
              private token: TokenStorageService) {
  }

  ngOnInit(): void {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  signIn() {
    const {username, password} = this.loginForm.value;
    const authLoginInfo = new AuthLoginInfo(username, password);
    this.authService.loginAuth(authLoginInfo).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.token.saveAuthorities(data.roles);
        this.token.saveUser(data);
        this.token.saveUsername(data.username);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.token.getAuthorities();
        this.router.navigateByUrl(this.returnUrl);

        this.authenticateUser();
      },
        error => {
        console.log(error);
        this.isLoginFailed = true;
      }
    );
  }

  authenticateUser() {
    if (this.token.getToken()) {
      for (const role of this.token.getAuthorities()) {
        if (role === 'ROLE_ADMIN' || role === 'ROLE_PM') {
          this.router.navigate(['admin', 'home']);
          return true;
        }
        if (role === 'ROLE_USER') {
          this.router.navigate(['home', 'trangchu']);
          return true;
        }
      }
    }
  }
  reloadPage() {
    window.location.reload();
  }

  ngOnDestroy(): void {
    this.reloadPage();
  }
}
