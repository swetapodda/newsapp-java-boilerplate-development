// import { Note } from '../note';
// import { BehaviorSubject } from 'rxjs/BehaviorSubject';
// import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { NewsArticle } from '../NewArticle';
import { RecommendedArticles } from '../RecommendedArticles';
import { AuthenticationService } from './authentication.service';

@Injectable()
export class NewsService {

  articles: Array<NewsArticle>;
  favourites: Array<NewsArticle>;
  recommanded: Array<RecommendedArticles>;
  articlesSubject: BehaviorSubject<Array<NewsArticle>>;
  favouritesSubject: BehaviorSubject<Array<NewsArticle>>;
  recommandedSubject: BehaviorSubject<Array<RecommendedArticles>>;

  constructor(public httpClient: HttpClient, public authService: AuthenticationService) {
    this.articles = [];
    this.favourites = [];
    this.recommanded = [];
    this.articlesSubject = new BehaviorSubject<Array<NewsArticle>>([]);
    this.favouritesSubject = new BehaviorSubject<Array<NewsArticle>>([]);
    this.recommandedSubject = new BehaviorSubject<Array<RecommendedArticles>>([]);
  }

  //News Api call by naresh chander
  fetchAllArticlesFromServer() {
    console.log("Under fetchAllArticlesFromServer method ");
    return this.httpClient.get<Array<NewsArticle>>(
      'https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=cef7d4d8078d405c983c41f66509e68e',
      { headers: new HttpHeaders().set('Authorization', `Bearer ${this.authService.getBearerToken()}`) }
    ).subscribe(res => {
      console.log("Naresh fetchAllArticlesFromServer method, response : ", res);
      this.articles = res["articles"];
      console.log("Under fetchAllArticlesFromServer method, response : ", res["articles"]);
      this.articlesSubject.next(this.articles);
    }, err => {
      this.articlesSubject.next(err);
    });
  }

  fetchAllFavouritesFromServer(username) {
    console.log("Under fetchAllFavouritesFromServer method ", username);
    return this.httpClient.get<Array<NewsArticle>>(
      'http://localhost:8079/favourite-service/api/v1/favourate/' + username,
      { headers: new HttpHeaders().set('Authorization', `Bearer ${this.authService.getBearerToken()}`) }
    ).subscribe(res1 => {
      console.log("Under fetchAllFavouritesFromServer method, response : ", res1);
      this.favourites = res1;
      this.favouritesSubject.next(this.favourites);
    }, err => {
      this.favouritesSubject.next(err);
      this.favouritesSubject.next(this.favourites);
    });
  }

  fetchAllRecommandedFromServer() {
    console.log("Naresh fetchAllrecommandedFromServer method ");
    return this.httpClient.get<Array<RecommendedArticles>>(
      //article-recommendation-service
      'http://localhost:8079/article-recommendation-service/api/v1/recommendation',
      { headers: new HttpHeaders().set('Authorization', `Bearer ${this.authService.getBearerToken()}`) }
    ).subscribe(res2 => {
      console.log("Naresh fetchAllrecommandedFromServer method, response : ", res2);
      this.recommanded = res2;
      this.recommandedSubject.next(this.recommanded);
    }, err => {
      this.recommandedSubject.next(err);
      this.recommandedSubject.next(this.recommanded);
    });
  }

  getArticles(): BehaviorSubject<Array<NewsArticle>> {
    console.log("Naresh getArticles method ");
    return this.articlesSubject;
  }

  getFavourites(): BehaviorSubject<Array<NewsArticle>> {
    console.log("Naresh getFavourites method ");
    return this.favouritesSubject;
  }

  getRecommanded(): BehaviorSubject<Array<RecommendedArticles>> {
    console.log("Naresh getRecommanded method ");
    return this.recommandedSubject;
  }

  addToFavourate(newsArticle: NewsArticle): Observable<NewsArticle> {
    console.log("Token", `Bearer${this.authService.getBearerToken()}`);
    return this.httpClient.post<NewsArticle>('http://localhost:8079/favourite-service/api/v1/favourate', newsArticle,
      { headers: new HttpHeaders().set('Authorization', `Bearer${this.authService.getBearerToken()}`) },
    ).pipe(
      tap(data => {
        this.favourites.push(data);
        this.favouritesSubject.next(this.favourites);
      })
    );
  }

  deleteFavourate(newsArticle: NewsArticle) {
    return this.httpClient.post<NewsArticle>('http://localhost:8079/favourite-service/api/v1/favourate/delete', newsArticle,
      { headers: new HttpHeaders().set('Authorization', `Bearer${this.authService.getBearerToken()}`) },
    ).pipe(
      tap(data => {
        if (data != null) {
          this.favourites.push(data);
          this.favouritesSubject.next(this.favourites);
        } else {
          this.favourites = null;
        }
      })
    );
  }

  openRecommandedArticles(title: string): Observable<NewsArticle> {
    console.log('I am here');
    return this.httpClient.get<NewsArticle>('http://localhost:8079/article-recommendation-service/api/v1/recommendation/' + title //,
      // { headers: new HttpHeaders().set('Authorization', `Bearer ${this.authService.getBearerToken()}`) },
    ).pipe(
      tap(count => {
        console.log("Total fav count naresh - ", count);
      })
    );
  }

}
