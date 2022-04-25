import { Injectable } from '@angular/core';
const TOKEN_KEY = 'AuthToken';
const AUTHORITIES_KEY = 'AuthAuthorities'; /*roles*/
const USER_KEY = 'auth-user';
const USERNAME_KEY = 'AuthUsername';
const ID_KEY = 'AuthUserId';
const NAME_KEY = 'Name';
const EMAIL_KEY = 'Email';
const AVATAR_KEY = 'Avatar';
const PHONE_KEY = 'Phone';
const ADDRESS_KEY = 'Address';
const PASSWORD_KEY = 'Password';
@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  private roles: Array<string> = [];

  constructor() { }
  signOut() {
    window.sessionStorage.clear();
  }
  // public saveAvatar(avatar: string) {
  //   window.sessionStorage.removeItem(AVATAR_KEY);
  //   window.sessionStorage.setItem(AVATAR_KEY , avatar);
  // }
  // public getAvatar(): string {
  //   return sessionStorage.getItem(AVATAR_KEY);
  // }
  // public getPhone(): string {
  //   return sessionStorage.getItem(PHONE_KEY);
  // }
  // public getAddress(): string {
  //   return sessionStorage.getItem(ADDRESS_KEY);
  // }
  // public getPassword(): string {
  //   return sessionStorage.getItem(PASSWORD_KEY);
  // }
  // public saveEmail(email: string) {
  //   window.sessionStorage.removeItem(EMAIL_KEY);
  //   window.sessionStorage.setItem(EMAIL_KEY, email);
  // }
  // public getEmail(): string {
  //   return sessionStorage.getItem(EMAIL_KEY);
  // }
  // public getName(): string {
  //   return sessionStorage.getItem(NAME_KEY);
  // }
  // public saveName(name: string) {
  //   window.sessionStorage.removeItem(NAME_KEY);
  //   window.sessionStorage.setItem(NAME_KEY, name);
  // }
  public getUser() {
    return JSON.parse(sessionStorage.getItem(USER_KEY));
  }
  public getUserId(): string {
    return sessionStorage.getItem(ID_KEY);
  }
  // public saveUserId(userId: string) {
  //   window.sessionStorage.removeItem(ID_KEY);
  //   window.sessionStorage.setItem(ID_KEY, userId);
  // }
  public saveUsername(username: string) {
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, JSON.stringify(username));
  }
  public getUsername(): string {
    return sessionStorage.getItem(USERNAME_KEY);
  }
  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }
  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }
  public saveAuthorities(authorities: string[]) {
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }
  public saveUser(user) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }
  public getAuthorities(): string[] {
    this.roles = [];
    if (sessionStorage.getItem(TOKEN_KEY)) {  /*this.getToken()*/
      try {
        JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)).forEach(authority => {
          this.roles.push(authority);
        });
      } catch (e) {
        console.log(e);
      }
    }
    return this.roles;
  }
}
