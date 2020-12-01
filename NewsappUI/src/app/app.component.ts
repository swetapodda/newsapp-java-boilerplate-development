import { Component } from '@angular/core'; 

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Newsapp';
  
  constructor(){
    console.log("Under app constuctor file");
  }
  
}
