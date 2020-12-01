package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.exception.ArticleAlreadyAddedException;
import com.stackroute.favouriteservice.exception.ArticleNotFoundException;
import com.stackroute.favouriteservice.model.Favourite;

public interface FavouriteService {

    public List<Favourite> findAllFavourateArticlesByUserId(String username) throws ArticleNotFoundException;

    public Favourite addArticleToFavourateList(Favourite favourite) throws ArticleAlreadyAddedException;

	public boolean deleteFavourateArticlesByUserId(String title);
}
