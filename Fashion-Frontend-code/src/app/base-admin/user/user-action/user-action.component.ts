import { Component, OnInit } from '@angular/core';
import {User} from '../../../models/user';
import {Subscription} from 'rxjs';
import {UserService} from '../../../services/user.service';

@Component({
  selector: 'app-user-action',
  templateUrl: './user-action.component.html',
  styleUrls: ['./user-action.component.css']
})
export class UserActionComponent implements OnInit {

  private subscription: Subscription;
  private userClass: User[];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.listUser();
  }

  listUser() {
    this.subscription = this.userService.getListUser().subscribe(
      data => {
        this.userClass = data;
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }
}
