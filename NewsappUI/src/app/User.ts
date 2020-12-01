export class User {
    username: string;
    firstName: string;
    lastName: string;
    password: string;
  
    constructor() {
      console.log("Naresh User constructor");
      this.username = '';
      this.firstName = '';
      this.lastName = '';
      this.password = '';      
    }
  }