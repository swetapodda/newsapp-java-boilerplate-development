package com.stackroute.articleRecommendationService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.articleRecommendationService.model.Recommendation;
import com.stackroute.articleRecommendationService.repository.ArticleRecommendationRepository;


@Service
public class ArticleRecommendationServiceImpl implements ArticleRecommendationService {

	private ArticleRecommendationRepository articleRecommendationRepository;
	
	@Autowired
	public ArticleRecommendationServiceImpl(ArticleRecommendationRepository articleRecommendationRepository) {
		this.articleRecommendationRepository = articleRecommendationRepository;
	}

	@Override
	public Recommendation findByTitle(String title) {
		return articleRecommendationRepository.findByTitle(title);
	}

	@Override
	public List<Recommendation> findAll() {
		return articleRecommendationRepository.findAll();
	}

	@Override
	public Recommendation saveRecommendation(Recommendation recommendation) {
		return articleRecommendationRepository.insert(recommendation);
	}

	@Override
	public void deleteByTitle(String title) {
		articleRecommendationRepository.deleteByTitle(title);
		
	}
}
