package com.stackroute.articleRecommendationService.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.articleRecommendationService.model.Recommendation;
import com.stackroute.articleRecommendationService.service.ArticleRecommendationService;


@RestController
@CrossOrigin
@EnableBinding(Sink.class)
public class ArticleRecommendationController {
	
	private ArticleRecommendationService articleRecommendationService;

	@Autowired
    public ArticleRecommendationController(ArticleRecommendationService articleRecommendationService) {
    	this.articleRecommendationService = articleRecommendationService;
	}
	
	@RequestMapping(value = "/api/v1/recommendation", method = RequestMethod.GET)
	public ResponseEntity<List<Recommendation>>getRecommendationList() {
		System.out.println("==========Naresh inside getRecommendationList");
		HttpHeaders headers = new HttpHeaders();
		List<Recommendation> recommendatedList = new ArrayList<Recommendation>();
		try {
			recommendatedList = articleRecommendationService.findAll();	
			//naresh check recommendatedList null			
			if(null != recommendatedList && recommendatedList.size() > 0) {
				headers.add("Total Recommendate Articles Found: ", String.valueOf(recommendatedList.size()));	
				return new ResponseEntity<List<Recommendation>>(recommendatedList, headers, HttpStatus.OK);
			} else {
				System.out.println("in else"+recommendatedList);	
				return new ResponseEntity<List<Recommendation>>(recommendatedList, HttpStatus.NOT_FOUND);				
			}
		} catch (Exception e) {
			return new ResponseEntity<List<Recommendation>>(recommendatedList, HttpStatus.NOT_FOUND);
		}
	}
	
	@StreamListener(target = Sink.INPUT)
	public void addArticleToFavourite(Recommendation recommendation) {	
		
		Recommendation recommendationDB = this.articleRecommendationService.findByTitle(recommendation.getTitle());
		
		if (null != recommendationDB && recommendationDB.getArticleCount() > 0) {
			this.articleRecommendationService.deleteByTitle(recommendationDB.getTitle());
			recommendationDB.setArticleCount(recommendationDB.getArticleCount() + 1);			
			this.articleRecommendationService.saveRecommendation(recommendationDB);
		} else {
			recommendation.setArticleCount(1);
			this.articleRecommendationService.saveRecommendation(recommendation);						
		}
	}
	
}
