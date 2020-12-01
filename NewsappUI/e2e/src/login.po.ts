import { browser,by,element,ElementFinder,promise } from "protractor";
import 'jasmine';
export class LoginPage{
   navigateTo(){
     return browser.get('/');
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
  getSubmitButton():ElementFinder{
    return element(by.className('mat-button'));
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
 isSubmitButtonPresent():promise.Promise<boolean>{
    return this.getSubmitButton().isPresent();
  }

  getLoginInputBoxDefaultValue():any{
      let inputusername,inputpassword;
      inputusername= this.getUserName().getAttribute('value');
      inputpassword= this.getPassword().getAttribute('value');
      return Promise.all([inputusername,inputpassword]).then((values) =>{
          return values;
 });}

 getMockLoginDetails(): any{
     const Logindetail : any = {username:'admin', password: 'admin@1234'};
     return Logindetail;
 }

 addLoginValues(): any{
     const login : any = this.getMockLoginDetails();
     this.getUserName().sendKeys(login.username);
     this.getPassword().sendKeys(login.password);
     return Object.keys(login).map(key=>login[key]);
 }

isLogoutButtonPresentDashboard():promise.Promise<boolean>{
  return this.getlogout().isPresent();
}

getlogout():ElementFinder{
  return element(by.buttonText('Log Out'));
}
}