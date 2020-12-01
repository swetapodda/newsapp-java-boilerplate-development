import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ArticleViewComponent } from '../article-view/article-view.component';
import { NewsArticle } from '../NewArticle';
import { AuthenticationService } from '../services/authentication.service';
import { NewsService } from '../services/news.service';
import { RouterService } from '../services/router.service';
import { User } from '../User';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  searchText;
  recommandedErrMessage: string;
  articlesErrMessage: string;
  favouritesErrMessage: string;

  articles: Array<NewsArticle>;
  favourites: Array<NewsArticle>;

  newsArticle: NewsArticle;

  user: User;

  constructor(private newsService: NewsService, public authenticationService: AuthenticationService,
    public dialog: MatDialog, public routerService: RouterService) {
    console.log("Under DashboardComponent Constructor ");

    this.articles = [];
    this.favourites = [];

    this.fetchAllArticlesFromServer();
    // this.fetchAllFavouritesFromServer();
    // this.fetchAllRecommandedFromServer(); 
  }

  fetchAllArticlesFromServer() {
    this.newsService.fetchAllArticlesFromServer();

    this.newsService.getArticles().subscribe(
      result1 => {
        this.articles = result1;
      },
      exception => {
        this.articlesErrMessage = exception.message;
      }
    );
  }

  openFavouritArticles() {
    console.log("under openFavouritArticles method");
    this.routerService.routeToFavouriteView();

    // this.fetchAllFavouritesFromServer();    
  }



  openRecommandedArticles() {
    console.log("under openRecommandedArticles method");
    this.routerService.routeToRecommendedView();

  }



  addToFavourate(newsArticle1: NewsArticle) {
    this.favouritesErrMessage = '';
    this.user = new User();
    this.user = this.authenticationService.getUser();
    console.log("User value under addToFavourate", this.user.username);
    newsArticle1.username = this.user.username;

    this.newsService.addToFavourate(newsArticle1).subscribe(
      addedArticle => {
        console.log("Output of addToFavourate method- ", addedArticle);
        // this.favourites = addedArticle;
        // this.favourites.push(addedArticle);
      },
      error => {
        if (409 === error.status) {
          this.favouritesErrMessage = "Already Added to Favourites List."
        } else {
          this.favouritesErrMessage = error.message;
        }
      }
    );
  }

  openArticle(newsArticle3: NewsArticle) {
    console.log("Naresh openArticle method");
    const editDialog = this.dialog.open(ArticleViewComponent, {
      data: newsArticle3
    });
  }

  logout() {
    this.user = new User();
    this.authenticationService.setUser(this.user);

    this.routerService.routeToIndex();
  }
}
