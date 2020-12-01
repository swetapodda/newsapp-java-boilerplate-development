import { LoginPage } from './login.po';
import { browser } from 'protractor';


describe('Login page', () => {
  let page: LoginPage;

  beforeEach(() => {
    page = new LoginPage();
  });
  it('Application move to Login view page',() => {
    browser.get("/");
    expect(browser.getCurrentUrl()).toContain('login');
  })
  it('should check  footer present on Login page', () => {
    page.navigateTo();
   
    expect(page.isFooterPresent()).toBeTruthy('<app-footer> should exist in login.component.html');
  });

  it('should check  Header present on Login page', () => {
    page.navigateTo();
   
    expect(page.isHeaderPresent()).toBeTruthy('<app-header> should exist in login.component.html');
  });

  it('should check  username text box present on Login page', () => {
    page.navigateTo();
    
    expect(page.isUserName()).toBeTruthy('username should exist in login.component.html');
  });

  it('should check  password text box present on Login page', () => {
    page.navigateTo();
   
    expect(page.isPassWord()).toBeTruthy('password should exist in login.component.html');
  });

  it('should get Submit button on Login page', () => {
    page.navigateTo();
    
    expect(page.isSubmitButtonPresent()).toBeTruthy('submit button should exist in login.component.html');
  });


  it('Default value of all the fields must be empty',()=>{
      const emptyLoginValue = ['',''];
      page.navigateTo();
      expect(page.getLoginInputBoxDefaultValue()).toEqual(emptyLoginValue,'Default value for username and password should be empty.')
  })

  it('should login into the system',()=>{
      page.navigateTo();
      browser.sleep(2000);
      let loginvalue = page.addLoginValues();
      expect(page.getLoginInputBoxDefaultValue()).toEqual(loginvalue,'should be able to set values for username and password');
      browser.get('/dashboard');
      browser.sleep(2000);
      expect(browser.getCurrentUrl()).toContain('dashboard');
      })

      it('should go to NewsApp DashBoard',()=>{
          browser.get('/dashboard');
         
          expect(browser.getCurrentUrl()).toContain('dashboard');

      })

    it('should check Log out button exist on DashBoard',()=>{
      browser.get('/dashboard');
     
      expect(page.isLogoutButtonPresentDashboard()).toBeTruthy('logout button should exist in dashboard.component.html');
      
  })

  it('should navigate to favourite',()=>{
    browser.get('/favouriteView');
    browser.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('favouriteView');
    
})

it('should navigate to recommended',()=>{
  browser.get('/recommendedView');
  browser.sleep(2000);
  expect(browser.getCurrentUrl()).toContain('recommendedView');
  
})

it('should navigate to login after logout',()=>{
  browser.get('/');
  browser.sleep(2000);
  expect(browser.getCurrentUrl()).toContain('login');
  
})
});
