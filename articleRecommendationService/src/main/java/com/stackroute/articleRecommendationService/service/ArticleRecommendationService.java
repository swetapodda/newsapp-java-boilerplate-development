package com.stackroute.articleRecommendationService.service;

import java.util.List;

import com.stackroute.articleRecommendationService.model.Recommendation;

public interface ArticleRecommendationService {

	Recommendation findByTitle(String title);
    
    List<Recommendation> findAll();
    
    Recommendation saveRecommendation(Recommendation recommendation);
    	
	void deleteByTitle(String title);
}
