package com.stackroute.favouriteservice.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.model.Favourite;

@RunWith(SpringRunner.class)
@DataMongoTest
public class FavouriteRepositoryTest {
	
	@Autowired
	private FavouriteRepository favouriteRepository;
	private Favourite favourite;
	
	@Before
    public void setUp() throws Exception {
        favourite = new Favourite();
        favourite.setAuthor("TestAuthor");
        favourite.setDescription("TestDesc");
        favourite.setTitle("TestTitle");
        favourite.setUsername("TestUser");
    }

    @After
    public void tearDown() throws Exception {
    	favouriteRepository.deleteAll();
    }
    
    @Test
    public void createFavouriteTest() {
    	favouriteRepository.insert(favourite);
    	Favourite fetchedFavourite = favouriteRepository.findByUsername(favourite.getUsername()).get(0);
        Assert.assertEquals("TestUser", fetchedFavourite.getUsername());
    }
    
    @Test
    public void findByUsernameTest() {
    	favouriteRepository.insert(favourite);
    	Favourite fetchedFavourite = favouriteRepository.findByUsername(favourite.getUsername()).get(0);
        Assert.assertEquals("TestUser", fetchedFavourite.getUsername());
    }
    
    @Test
    public void findByUsernameTestFailure() {
    	favouriteRepository.insert(favourite);
    	Favourite fetchedFavourite = favouriteRepository.findByUsername(favourite.getUsername()).get(0);
        Assert.assertNotEquals("TestUser1", fetchedFavourite.getUsername());
    }
}
