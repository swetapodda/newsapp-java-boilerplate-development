import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ApphoverComponent } from './apphover/apphover.component';
import { ArticleViewComponent } from './article-view/article-view.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FilterPipe } from './filter.pipe';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { OpenFavouriteViewComponent } from './open-favourite-view/open-favourite-view.component';
import { OpenRecommendedViewComponent } from './open-recommended-view/open-recommended-view.component';
import { RegistrationComponent } from './registration/registration.component';
import { AuthenticationService } from './services/authentication.service';
import { NewsService } from './services/news.service';
import { RouterService } from './services/router.service';

@NgModule({
  declarations: [
    AppComponent, HeaderComponent,
    LoginComponent, DashboardComponent, RegistrationComponent, ArticleViewComponent,
    ArticleViewComponent,
    FilterPipe,
    OpenFavouriteViewComponent, OpenRecommendedViewComponent, HeaderComponent, FooterComponent, ApphoverComponent
  ],
  imports: [
    MatToolbarModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatInputModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatExpansionModule,
    MatCardModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDialogModule,
    AppRoutingModule,
    AngularFontAwesomeModule,
    MatMenuModule,
    MatIconModule
  ],
  providers: [
    AuthenticationService, RouterService, NewsService

  ],
  bootstrap: [AppComponent],
  entryComponents: [ArticleViewComponent]
})
export class AppModule { }
