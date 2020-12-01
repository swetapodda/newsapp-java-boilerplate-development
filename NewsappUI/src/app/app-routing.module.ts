import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { OpenFavouriteViewComponent } from './open-favourite-view/open-favourite-view.component';
import { OpenRecommendedViewComponent } from './open-recommended-view/open-recommended-view.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'favouriteView', component: OpenFavouriteViewComponent },
  { path: 'recommendedView', component: OpenRecommendedViewComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  constructor() {
    console.log("Naresh AppRoutingModule constuctor");
  }
}
