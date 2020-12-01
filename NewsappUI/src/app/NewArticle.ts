export class NewsArticle {
  id: Number;
  username: string;
  author: string;
  title: string;
  description: string;
  urlToImage: string;

  constructor() {
    console.log("Naresh NewsArticle constructor");
    this.username = '';
    this.author = '';
    this.title = '';
    this.description = '';
    this.urlToImage = '';
  }
}