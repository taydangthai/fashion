import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = 'http://localhost:8000/api/auth/user';

  constructor(private http: HttpClient) { }

  getListUser(): Observable<User[]> {
    return this.http.get<User[]>(this.userUrl);
  }

  getUser(id: string): Observable<any> {
    return this.http.get(`${this.userUrl}/${id}`);
  }

  deleteUserById(id: string): Observable<void> {
    return this.http.delete<void>(`${this.userUrl}/${id}`);
  }
}
