import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NewsArticle } from '../NewArticle';

@Component({
  selector: 'app-article-view',
  templateUrl: './article-view.component.html',
  styleUrls: ['./article-view.component.css']
})
export class ArticleViewComponent implements OnInit {

  newsArticle: NewsArticle;

  constructor(private matDialogRef: MatDialogRef<ArticleViewComponent>,
    @Inject(MAT_DIALOG_DATA) private data: NewsArticle) {  
      this.newsArticle = this.data;
    }

  ngOnInit() {
  }

}
