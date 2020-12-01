export class RecommendedArticles {
  author: string;
  title: string;
  description: string;
  articleCount: number;

  constructor() {
    console.log("Naresh RecommendedArticles constructor");
    this.author = '';
    this.title = '';
    this.description = '';  
    this.articleCount = 0;  
  }
}