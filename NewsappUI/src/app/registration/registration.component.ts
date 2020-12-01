import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  submitMessage: String;
  firstName = new FormControl('', [Validators.required]);
  lastName = new FormControl('', [Validators.required]);
  username = new FormControl('', [Validators.required, Validators.minLength(5)]);
  password = new FormControl('', [Validators.required, Validators.minLength(8)]);

  authenticationService: AuthenticationService;

  constructor(authenticationService: AuthenticationService,
    public routerService: RouterService) {
    console.log("Registration constuctor-Naresh");
    this.authenticationService = authenticationService;
  }

  registerUser() {
    this.submitMessage = '';
    if (this.firstName.invalid || this.lastName.invalid
      || this.username.invalid || this.password.invalid) {
      if (this.firstName.hasError('required')) {
        this.submitMessage = 'Firstname is mandatory ';
      }
      if (this.lastName.hasError('required')) {
        this.submitMessage = this.submitMessage + 'LastName is mandatory ';
      }
      if (this.username.hasError('required') || this.password.hasError('required')) {
        this.submitMessage = this.submitMessage + 'Username and password are mandatory ';
      }
      if (this.username.hasError('minlength')) {
        this.submitMessage = this.submitMessage + 'Username must have min 5 characters ';
      }
      if (this.password.hasError('minlength')) {
        this.submitMessage = this.submitMessage + 'Password must have min 8 characters ';
      }
    } else {
      this.authenticationService.setUser({
        'username': this.username.value, 'firstName': this.firstName.value,
        'lastName': this.lastName.value, 'password': this.password.value
      });

      this.authenticationService.registerUser({
        'username': this.username.value, 'firstName': this.firstName.value,
        'lastName': this.lastName.value, 'password': this.password.value
      }).subscribe(
        res => {
          console.log("Response,Registration Naresh- ", res);

          if (res == true) {
            this.routerService.routeToDashboard();
          } else {
            this.submitMessage = "User Already Registered"
          }
        },
        err => {
          console.log("Under error Naresh- ", err);
          this.submitMessage = "User Already Registered"
        }
      );
    }
  }

  loginUser() {
    console.log("Under loginUser Naresh menthod of register component");
    this.routerService.routeToLogin();
  }

  ngOnInit() {
  }

}
