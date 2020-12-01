import { browser,by,element,ElementFinder,promise } from "protractor";
import 'jasmine';
export class RegisterPage{
   navigateTo(){
     return browser.get('/register');
   }

   getHeader():ElementFinder{
     return element(by.tagName('app-header'));
   }
   getFooter():ElementFinder{
    return element(by.tagName('app-footer'));
  }
  getUserName():ElementFinder{
    return element(by.className('username'));
  }
  getPassword():ElementFinder{
    return element(by.className('password'));
  }

  getFirstName():ElementFinder{
    return element(by.className('firstName'));
  }
  getLastName():ElementFinder{
    return element(by.className('lastName'));
  }
   
   isHeaderPresent():promise.Promise<boolean>{
     return this.getHeader().isPresent();
   }

   isFooterPresent():promise.Promise<boolean>{
    return this.getFooter().isPresent();
  }

  isUserName():promise.Promise<boolean>{
    return this.getUserName().isPresent();
  }

  isPassWord():promise.Promise<boolean>{
   return this.getPassword().isPresent();
 }
 isFirstName():promise.Promise<boolean>{
    return this.getFirstName().isPresent();
  }

  isLastName():promise.Promise<boolean>{
   return this.getLastName().isPresent();
 }

 getSubmitButton():ElementFinder{
    return element(by.className('mat-button'));
  }

 isSubmitButtonPresent():promise.Promise<boolean>{
    return this.getSubmitButton().isPresent();
  }

  getRegisterInputBoxDefaultValue():any{
    let inputusername,inputpassword,inputfirstname,inputlastname;
    inputusername= this.getUserName().getAttribute('value');
    inputpassword= this.getPassword().getAttribute('value');
    inputfirstname=this.getFirstName().getAttribute('value');
    inputlastname=this.getLastName().getAttribute('value');
    return Promise.all([inputusername,inputpassword,inputfirstname,inputlastname]).then((values) =>{
        return values;
});}


getMockLoginDetails(): any{
  const Logindetail : any = {username:'admin', password: 'admin@1234',firstName: 'Shweta',lastName: 'Tyagi'};
  return Logindetail;
}

addRegisterValues(): any{
  const login : any = this.getMockLoginDetails();
  this.getUserName().sendKeys(login.username);
  this.getPassword().sendKeys(login.password);
  this.getFirstName().sendKeys(login.firstName);
  this.getLastName().sendKeys(login.lastName);
  return Object.keys(login).map(key=>login[key]);
}
}