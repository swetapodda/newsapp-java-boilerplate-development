import { RegisterPage } from './register.po';
import { browser } from 'protractor';

describe('Registeration page', () => {
  let page: RegisterPage;

  beforeEach(() => {
    page = new RegisterPage();
  });
  it('Application move to Registeration view page',() => {
    browser.get("/register");
    expect(browser.getCurrentUrl()).toContain('register');
  })
  it('should check  footer present on Registration page', () => {
    page.navigateTo();
    browser.sleep(20000);
    expect(page.isFooterPresent()).toBeTruthy('<app-footer> should exist in registration.component.html');
  });

  it('should check  Header present on Registration page', () => {
    page.navigateTo();
    
    expect(page.isHeaderPresent()).toBeTruthy('<app-header> should exist in registration.component.html');
  });

  it('should check  username text box present on Login page', () => {
    page.navigateTo();
   
    expect(page.isUserName()).toBeTruthy('username should exist in login.component.html');
  });

  it('should check  password text box present on Registration page', () => {
    page.navigateTo();
    
    expect(page.isPassWord()).toBeTruthy('password should exist in registration.component.html');
  });
  it('should check  firstname text box present on Registration page', () => {
    page.navigateTo();
    
    expect(page.isFirstName()).toBeTruthy('firstname should exist in registration.component.html');
  });
  it('should check  lastname text box present on Registration page', () => {
    page.navigateTo();
    
    expect(page.isLastName()).toBeTruthy('lastname should exist in registration.component.html');
  });
  it('should get Submit button on Registration page', () => {
    page.navigateTo();
    
    expect(page.isSubmitButtonPresent()).toBeTruthy('submit button should exist in registration.component.html');
  });

  it('Default value of all the fields must be empty',()=>{
    const emptyRegisterValue = ['','','',''];
    page.navigateTo();
    expect(page.getRegisterInputBoxDefaultValue()).toEqual(emptyRegisterValue,'Default value for username and password should be empty.')
})


it('should login into the system',()=>{
  page.navigateTo();
  browser.sleep(2000);
  let loginvalue = page.addRegisterValues();
  expect(page.getRegisterInputBoxDefaultValue()).toEqual(loginvalue,'should be able to set values for username and password');
  browser.get('/dashboard');
  browser.sleep(2000);
  })


});
