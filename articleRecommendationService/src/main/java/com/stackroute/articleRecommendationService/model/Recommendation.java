package com.stackroute.articleRecommendationService.model;

import org.springframework.data.mongodb.core.mapping.Document;

/*
 * The class "User" will be acting as the data model for the User Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */

@Document
public class Recommendation {
	
    private String author;    
    private String title;
    private String description;
    private int articleCount;
	private String urlToImage;
	
    public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public Recommendation() {
    	super();
    }

	public Recommendation(String author, String title, String description, int articleCount,String urlToimage) {
		super();
		this.author = author;
		this.title = title; 
		this.description = description;
		this.articleCount = articleCount;
		this.urlToImage = urlToImage;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Favourite [author=" + author + ", title=" + title + ", description="
				+ description + ", articleCount=" + articleCount + "]";
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
}
