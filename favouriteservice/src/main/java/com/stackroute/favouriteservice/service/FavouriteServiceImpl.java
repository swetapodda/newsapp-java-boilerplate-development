package com.stackroute.favouriteservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.exception.ArticleAlreadyAddedException;
import com.stackroute.favouriteservice.exception.ArticleNotFoundException;
import com.stackroute.favouriteservice.model.Favourite;
import com.stackroute.favouriteservice.repository.FavouriteRepository;

@Service
public class FavouriteServiceImpl implements FavouriteService {

	private FavouriteRepository favouriteRepository;
	
	@Autowired
	public FavouriteServiceImpl(FavouriteRepository favouriteRepository) {
		this.favouriteRepository = favouriteRepository;
	}

	@Override
	public List<Favourite> findAllFavourateArticlesByUserId(String username) throws ArticleNotFoundException {
		return favouriteRepository.findByUsername(username);
	}

	@Override
	public Favourite addArticleToFavourateList(Favourite favourite) throws ArticleAlreadyAddedException {
		return favouriteRepository.insert(favourite);		
	}

	@Override
	public boolean deleteFavourateArticlesByUserId(String title) {
		// TODO Auto-generated method stub
		 boolean flag = false;
		 try {
			 favouriteRepository.deleteByTitle(title);	
			 flag=true;
		 }catch (Exception e) {
			// TODO: handle exception
			 flag= false;
			 return flag;
		}
		 return flag;
		  
	}
}
