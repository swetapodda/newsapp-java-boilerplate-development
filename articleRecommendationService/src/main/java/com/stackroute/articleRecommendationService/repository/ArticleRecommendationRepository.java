package com.stackroute.articleRecommendationService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.articleRecommendationService.model.Recommendation;


/*
* This class is implementing the JpaRepository interface for User.
* Annotate this class with @Repository annotation
* */
@Repository
public interface ArticleRecommendationRepository extends MongoRepository<Recommendation, String>  {
	    
	Recommendation findByTitle(String title);
    
    List<Recommendation> findAll();
    
    void deleteByTitle(String title);
}
