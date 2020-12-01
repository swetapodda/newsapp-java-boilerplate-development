import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../User';
@Injectable()
export class AuthenticationService {
  user: User;

  validateUserURL = 'http://localhost:9300/api/v1/auth/validate';

  registerURL = 'http://localhost:9300/api/v1/auth/register';

  constructor(public httpClient: HttpClient) {
    this.user = new User();
  }

  authenticateUser(data) {
    this.user = data;
    return this.httpClient.post(this.validateUserURL, data);
  }

  registerUser(data) {
    this.user = data;
    return this.httpClient.post(this.registerURL, data);
  }

  setUser(data) {
    this.user = data;
  }

  getUser() {
    console.log("naresh getUser method");

    return this.user;
  }

  setBearerToken(token) {
    localStorage.setItem('bearerToken', token);
  }

  getBearerToken() {
    return localStorage.getItem('bearerToken');
  }

}
