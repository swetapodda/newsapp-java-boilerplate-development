package com.stackroute.favouriteservice.service;

import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.exception.ArticleAlreadyAddedException;
import com.stackroute.favouriteservice.exception.ArticleNotFoundException;
import com.stackroute.favouriteservice.model.Favourite;
import com.stackroute.favouriteservice.repository.FavouriteRepository;
import static org.mockito.ArgumentMatchers.any;

public class FavouriteServiceImplTest {
	
	@Mock
	private FavouriteRepository favouriteRepository;
	
	@InjectMocks
	private FavouriteServiceImpl favouriteServiceImpl;
	
	private Favourite favourite;	
	List<Favourite> list;
	
	@Before
    public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        favourite = new Favourite();
        favourite.setAuthor("TestAuthor");
        favourite.setDescription("TestDesc");
        favourite.setTitle("TestTitle");
        favourite.setUsername("TestUser");
        
        list = new ArrayList<Favourite>();
        list.add(favourite);
    }
	
	@Test
    public void addArticleToFavourateListTest() throws ArticleAlreadyAddedException {
		when(favouriteRepository.insert((Favourite) any())).thenReturn(favourite);
		
		Favourite updatedFavourite = favouriteServiceImpl.addArticleToFavourateList(favourite);
        Assert.assertEquals(favourite, updatedFavourite);
    }
	
	@Test
    public void addArticleToFavourateListTestFailure() throws ArticleAlreadyAddedException {
		when(favouriteRepository.insert((Favourite) any())).thenReturn(null);	
		
		Favourite updatedFavourite = favouriteServiceImpl.addArticleToFavourateList(favourite);
        Assert.assertNotEquals(favourite, updatedFavourite);
    }
	
	@Test
    public void findAllFavourateArticlesByUserIdTest() throws ArticleNotFoundException {
		when(favouriteRepository.findByUsername(favourite.getUsername())).thenReturn(list);
		
		List<Favourite> returnedList = favouriteServiceImpl.findAllFavourateArticlesByUserId(favourite.getUsername());
    	
        Assert.assertEquals(list, returnedList);
    }
	
	@Test
    public void findAllFavourateArticlesByUserIdTestFailure() throws ArticleNotFoundException {
		when(favouriteRepository.findByUsername(favourite.getUsername())).thenReturn(null);
		
		List<Favourite> returnedList = favouriteServiceImpl.findAllFavourateArticlesByUserId(favourite.getUsername());
    	
        Assert.assertNotEquals(list, returnedList);
    }
	
}
