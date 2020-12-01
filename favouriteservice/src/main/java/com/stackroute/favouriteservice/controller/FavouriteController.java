package com.stackroute.favouriteservice.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.model.Favourite;
import com.stackroute.favouriteservice.service.FavouriteService;
import com.stackroute.favouriteservice.source.FavouriteServiceSource;


@RestController
@CrossOrigin
@EnableBinding(FavouriteServiceSource.class)
public class FavouriteController {
	
	private FavouriteService favouriteService;

	@Autowired
    public FavouriteController(FavouriteService favouriteService) {
    	this.favouriteService = favouriteService;
	}
	
	@Autowired
	FavouriteServiceSource favouriteServiceSource;
	
	@RequestMapping(value = "/api/v1/favourate", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Favourite> addArticleToFavourateList(@RequestBody Favourite favourite) {
		System.out.print("favourite"+favourite);
		
		if (favourite == null || StringUtils.isEmpty(favourite.getUsername()) || StringUtils.isEmpty(favourite.getAuthor())
				|| StringUtils.isEmpty(favourite.getTitle()) || StringUtils.isEmpty(favourite.getDescription()) ) {
					System.out.print("favourite"+favourite.getUsername());
					System.out.print("favourite"+favourite.getAuthor());
					System.out.print("favourite"+favourite.getDescription());
					System.out.print("favourite"+favourite.getTitle());
			return new ResponseEntity<Favourite>(HttpStatus.BAD_REQUEST);
		}
		
		Favourite insettedFavourite = null;
		List<Favourite> favouriteList = null;
		try {			
			favouriteList = this.favouriteService.findAllFavourateArticlesByUserId(favourite.getUsername());
			System.out.print("favouriteList"+favouriteList);
			if(null != favouriteList && favouriteList.size() > 0) {
				for (Favourite favourite2 : favouriteList) {
					if (favourite2.getTitle().equalsIgnoreCase(favourite.getTitle())) {
						return new ResponseEntity<Favourite>(insettedFavourite, HttpStatus.CONFLICT);												
					}					
				}				
			}			
			insettedFavourite = favouriteService.addArticleToFavourateList(favourite);			
			favouriteServiceSource.addToFavourite().send(MessageBuilder.withPayload(favourite).build());
			
			return new ResponseEntity<Favourite>(insettedFavourite, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Favourite>(insettedFavourite, HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/api/v1/favourate/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<Favourite>> findAllFavourateArticlesByUserId(@PathVariable("username") String username) {		
		HttpHeaders headers = new HttpHeaders();
		List<Favourite> favouriteList = new ArrayList<Favourite>();
		try {
			favouriteList = this.favouriteService.findAllFavourateArticlesByUserId(username);
			if(null != favouriteList && favouriteList.size() > 0) {
				headers.add("Total Favourate Articles Found: ", String.valueOf(favouriteList.size()));	
				return new ResponseEntity<List<Favourite>>(favouriteList, headers, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Favourite>>(favouriteList, headers, HttpStatus.NOT_FOUND);				
			}
		} catch (Exception e) {
			return new ResponseEntity<List<Favourite>>(favouriteList, headers, HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value = "api/v1/favourate/delete", method = {RequestMethod.POST}, produces = "application/json")
	public ResponseEntity<List<Favourite>> deleteFavourateArticlesByUserId(@RequestBody Favourite favourite) {
		
		HttpHeaders headers = new HttpHeaders();
		List<Favourite> favouriteList = null;
		try {			
			boolean flag = this.favouriteService.deleteFavourateArticlesByUserId(favourite.getTitle());				
			
				if(flag==true) {
					headers.add("Favourate article has been deleted Successfully", String.valueOf(flag));
					return new ResponseEntity<List<Favourite>>(favouriteList, headers, HttpStatus.OK);
				}
				
		}
		catch (Exception e) {
				return new ResponseEntity<List<Favourite>>(favouriteList, headers, HttpStatus.NOT_FOUND);
		}
		return null;
	}
	
}
