import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { NewsArticle } from '../NewArticle';
import { RecommendedArticles } from '../RecommendedArticles';
import { AuthenticationService } from '../services/authentication.service';
import { NewsService } from '../services/news.service';
import { RouterService } from '../services/router.service';
import { ArticleViewComponent } from '../article-view/article-view.component';
@Component({
  selector: 'app-open-recommended-view',
  templateUrl: './open-recommended-view.component.html',
  styleUrls: ['./open-recommended-view.component.css']
})
export class OpenRecommendedViewComponent implements OnInit {
  searchText;
  recommandedErrMessage: string;
  recommanded: Array<RecommendedArticles>;

  constructor(private newsService: NewsService, public dialog: MatDialog, public authenticationService: AuthenticationService,
    public routerService: RouterService) {
    console.log("Naresh OpenRecommendedViewComponent Constructor ");

    this.recommanded = [];

    this.fetchAllRecommandedFromServer();
  }

  fetchAllRecommandedFromServer() {
    this.newsService.fetchAllRecommandedFromServer();

    this.newsService.getRecommanded().subscribe(
      result3 => {
        console.log("Naresh fetchAllRecommandedFromServer from ts - ", result3);
        this.recommanded = result3;
      },
      exception => {
        this.recommandedErrMessage = exception.message;
      }
    );
  }

  routeToDashboard() {
    this.routerService.routeToDashboard();
  }

  routeToFavouriteView() {
    this.routerService.routeToFavouriteView();
  }

  logout() {
    this.routerService.routeToIndex();
  }
  openArticle(newsArticle3: NewsArticle) {
    console.log("Naresh openArticle method");
    const editDialog = this.dialog.open(ArticleViewComponent, {
      data: newsArticle3
    });
  }
  ngOnInit() {
  }

}
