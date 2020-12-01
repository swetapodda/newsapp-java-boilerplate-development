import { browser,by,element,ElementFinder,promise } from "protractor";
import 'jasmine';
export class AppPage{
   navigateTo(){
     return browser.get('/');
   }

   getHeader():ElementFinder{
     return element(by.tagName('app-header'));
   }

   isHeaderPresent():promise.Promise<boolean>{
     return this.getHeader().isPresent();
   }
}