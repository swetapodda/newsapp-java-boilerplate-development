package com.stackroute.articleRecommendationService.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.articleRecommendationService.model.Recommendation;
import com.stackroute.articleRecommendationService.repository.ArticleRecommendationRepository;

public class ArticleRecommendationServiceImplTest {
	
	@Mock
	private ArticleRecommendationRepository articleRecommendationRepository;
	
	@InjectMocks
	private ArticleRecommendationServiceImpl articleRecommendationService;
	
	private Recommendation recommendation;	
	List<Recommendation> list;
	
	@Before
    public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        recommendation = new Recommendation();
        recommendation.setAuthor("TestAuthor");
        recommendation.setDescription("TestDesc");
        recommendation.setTitle("TestTitle");
        recommendation.setArticleCount(0);
        
        list = new ArrayList<Recommendation>();
        list.add(recommendation);
    }
	
	@Test
    public void findByTitleTest() throws Exception {
		when(articleRecommendationRepository.findByTitle(recommendation.getTitle())).thenReturn(recommendation);
		
		Recommendation updatedRecommendation = articleRecommendationService.findByTitle(recommendation.getTitle());
        Assert.assertEquals(recommendation.getTitle(), updatedRecommendation.getTitle());
    }
	
	@Test
    public void findAllTest() throws Exception {
		when(articleRecommendationRepository.insert((Recommendation) any())).thenReturn(recommendation);
		when(articleRecommendationRepository.findAll()).thenReturn(list);
        Assert.assertNotNull(articleRecommendationService.findAll());
    }
	
	@Test
    public void saveRecommendation() throws Exception {
		when(articleRecommendationRepository.insert((Recommendation) any())).thenReturn(recommendation);
		
		Assert.assertNotNull(articleRecommendationService.saveRecommendation(recommendation));
    }
	
	@Test
    public void deleteByTitle() throws Exception {
		articleRecommendationService.deleteByTitle(recommendation.getTitle());
        Assert.assertTrue(true);
    }
	
}
