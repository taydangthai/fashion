import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { SignUpInfo } from '../model-auth/signup-infor';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../service-auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  returnUrl: string;
  registerForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
    username: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
    email: new FormControl('', [Validators.required, Validators.email, Validators.maxLength(100)]),
    password: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(50)]),
    phone: new FormControl('', [Validators.required, Validators.pattern(
      '^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$')]),
    address: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]),
    confirmPassword: new FormControl('', [Validators.required]),

  });

  constructor(private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService) { }

  ngOnInit(): void {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/login';
  }

  register() {
    const {name, username, email, password, phone, address} = this.registerForm.value;
    const signUpInfoForm = new SignUpInfo(name, username, email, password,  phone, address);

    this.authService.register(signUpInfoForm).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        alert('Đăng Ký Thành Công !');
        this.router.navigateByUrl(this.returnUrl);

      },
      err => {
        console.log(err);
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }
}
