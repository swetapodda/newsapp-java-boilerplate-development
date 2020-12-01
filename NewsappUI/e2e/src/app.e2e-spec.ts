import { AppPage } from './app.po';
import { browser } from 'protractor';

describe('home page', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });
  it("should check application landing on login view page",()=>{
    browser.get("/");
    expect(browser.getCurrentUrl()).toContain('login');
  })
  it('should check  header present on home page', () => {
    page.navigateTo();
    browser.sleep(20000);
    expect(page.isHeaderPresent()).toBeTruthy('<app-header> should exist in login.component.html');
  });
});
