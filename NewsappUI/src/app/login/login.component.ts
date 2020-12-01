import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  submitMessage: String;
  username = new FormControl('', [Validators.required, Validators.minLength(5)]);
  password = new FormControl('', [Validators.required, Validators.minLength(8)]);

  authenticationService: AuthenticationService;

  constructor(authenticationService: AuthenticationService,
    public routerService: RouterService) {
    console.log("Under login constuctor file");
    this.authenticationService = authenticationService;
  }

  loginSubmit() {
    if (this.username.invalid || this.password.invalid) {
      if (this.username.hasError('required') || this.password.hasError('required')) {
        this.submitMessage = 'Username and password are mandatory';
      } else if (this.username.hasError('minlength')) {
        this.submitMessage = 'Username must have min 5 characters';
      } else {
        this.submitMessage = 'Password must have min 8 characters';
      }
    } else {
      this.authenticationService.setUser({ 'username': this.username.value, 'password': this.password.value });

      this.authenticationService.authenticateUser({
        'username': this.username.value,
        'password': this.password.value
      }).subscribe(
        res => {
          console.log("Response - ", res);

          if (null != res) {
            this.authenticationService.setBearerToken(res['token']);
            this.routerService.routeToDashboard();
          } else {
            this.submitMessage = "Username or Password is Wrong."
          }
        },
        err => {
          console.log("Under error - ", err);

          if (null != err && null != err.error && null != err.error.text) {
            this.authenticationService.setBearerToken(err.error.text);
            this.routerService.routeToDashboard();
          } else {
            this.submitMessage = "Username or Password is Wrong."
          }

        }
      );
    }
  }

  registerUser() {
    console.log("Naresh registerUser menthod of lgin component");
    this.routerService.routeToRegisterUser();
  }
}
