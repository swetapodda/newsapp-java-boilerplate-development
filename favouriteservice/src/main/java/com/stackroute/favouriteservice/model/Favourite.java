package com.stackroute.favouriteservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

/*
 * The class "User" will be acting as the data model for the User Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */

@Document
public class Favourite {
    
    private String username;
    private String author;    
    private String title;
    private String description;    
    private String urlToImage;    
    
    public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public Favourite() {
    	super();
    }

	public Favourite(String username, String author, String title, String description) {
		super();
		this.username = username;
		this.author = author;
		this.title = title;
		this.description = description;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		return "Favourite [username=" + username + ", author=" + author + ", title=" + title + ", description="
				+ description + "]";
	}
}
