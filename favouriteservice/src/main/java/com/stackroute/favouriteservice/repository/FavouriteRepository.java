package com.stackroute.favouriteservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.favouriteservice.model.Favourite;


/*
* This class is implementing the JpaRepository interface for User.
* Annotate this class with @Repository annotation
* */
@Repository
public interface FavouriteRepository extends MongoRepository<Favourite, String>  {
	List<Favourite> findByUsername(String username);
	
	Long deleteByTitle(String title);
	
}
