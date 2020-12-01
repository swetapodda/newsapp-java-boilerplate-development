import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { NewsArticle } from '../NewArticle';
import { AuthenticationService } from '../services/authentication.service';
import { NewsService } from '../services/news.service';
import { RouterService } from '../services/router.service';
import { User } from '../User';
import { ArticleViewComponent } from '../article-view/article-view.component';
@Component({
  selector: 'app-open-favourite-view',
  templateUrl: './open-favourite-view.component.html',
  styleUrls: ['./open-favourite-view.component.css']
})
export class OpenFavouriteViewComponent implements OnInit {
  searchText;
  favouritesErrMessage: string;
  recommandedErrMessage: string;
  favourites: Array<NewsArticle>;

  user: User;

  constructor(private newsService: NewsService, public authenticationService: AuthenticationService,
    public routerService: RouterService, public dialog: MatDialog) {
    console.log("Under OpenFavouriteViewComponent Constructor ");

    this.favourites = [];
    this.user = new User();
    this.user = this.authenticationService.getUser();
  }

  fetchAllFavouritesFromServer() {
    console.log("User value under fetchAllFavouritesFromServer ", this.user.username);
    this.newsService.fetchAllFavouritesFromServer(this.user.username);
    // this.user = new User();
    // this.authenticationService.setUser(new User());

    this.newsService.getFavourites().subscribe(
      result2 => {
        console.log("Output of fetchAllFavouritesFromServer from ts ", result2);
        this.favourites = result2;
      },
      exception => {
        this.favouritesErrMessage = exception.message;
      }
    );
  }
  openArticle(newsArticle3: NewsArticle) {
    console.log("Naresh openArticle method");
    const editDialog = this.dialog.open(ArticleViewComponent, {
      data: newsArticle3
    });
  }

  deleteArticle(newsArticle3: NewsArticle) {
    console.log("under deleteArticle method");
    this.newsService.deleteFavourate(newsArticle3).subscribe(

      result2 => {
        console.log("Output of deleteArticle from ts ", result2);
        this.newsService.fetchAllFavouritesFromServer(this.user.username);
      },
      exception => {
        this.newsService.fetchAllFavouritesFromServer(this.user.username);
        this.favouritesErrMessage = exception.message;
      }
    );

  }
  routeToDashboard() {
    this.routerService.routeToDashboard();
  }

  routeToRecommendedView() {
    this.routerService.routeToRecommendedView();
  }

  logout() {
    this.user = new User();
    this.authenticationService.setUser(new User());

    this.routerService.routeToIndex();
  }

  ngOnInit() {
    this.fetchAllFavouritesFromServer();
  }

}
