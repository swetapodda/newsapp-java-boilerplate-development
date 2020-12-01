import { TestBed, async} from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { AppComponent } from '../app/app.component'
import { NO_CHANGE } from '@angular/core/src/render3/instructions';

fdescribe('AppComponent',()=>{
    beforeEach(async(()=>{
        TestBed.configureTestingModule({
            declarations:[
                AppComponent
            ],
            schemas:[NO_ERRORS_SCHEMA]
        }).compileComponents();
    }));

    it('should create  the app',async(()=>{
        const fixture= TestBed.createComponent(AppComponent);
        const app = fixture.debugElement.componentInstance;
        expect(app).toBeTruthy();
    }))
})