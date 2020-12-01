import { Component } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ApphoverComponent } from './apphover.component';



@Component({
  template: `<input type="text" hoverfocus>`
})
class TestApphoverComponent {
}



fdescribe('ApphoverComponent', () => {
  //let component: TestApphoverComponent;
  let component: TestApphoverComponent;
  let fixture: ComponentFixture<TestApphoverComponent>;
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TestApphoverComponent, ApphoverComponent]
    });
    fixture = TestBed.createComponent(TestApphoverComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
