package com.stackroute.userservice.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.util.JSON;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserAuthenticationService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin
public class UserAuthenticationController {
	
	private UserAuthenticationService userAuthenticationService;
	private Map<String, String> userMap = new HashMap<String, String>();

	@Autowired
    public UserAuthenticationController(UserAuthenticationService authicationService) {
    	this.userAuthenticationService = authicationService;
	}
	
	@RequestMapping(value = "/api/v1/auth/register", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> createUser(@RequestBody User user) {
		
		if (user == null) {
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
		
		try {
			if (isAllValuesPresent(user)) {
				if (userAuthenticationService.saveUser(user)) {
					return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);					
				};								
			}
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
	}
	
	private boolean isAllValuesPresent(User user) {
		if (StringUtils.isEmpty(user.getFirstName()) || StringUtils.isEmpty(user.getLastName())
				|| StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
			return false;			
		}
		return true;
	}
	
	@RequestMapping(value = "/api/v1/auth/validate", produces = "application/json")
	public ResponseEntity<?> validateUser(@RequestBody User user) {
		HttpHeaders headers = new HttpHeaders();
		if (user == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		String token = null;
		try {
			token = getToken(user.getUsername(), user.getPassword());
			userMap.clear();
			userMap.put("message", "User Logged-In Successfully");
			userMap.put("token", token);	
			if (!StringUtils.isEmpty(token)) {	 
				return new ResponseEntity<Object>(userMap, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(userMap, HttpStatus.NOT_FOUND);				
			}	
		} catch (Exception e) {
			return new ResponseEntity<Object>(userMap, HttpStatus.NOT_FOUND);
		}		
	}


// Generate JWT token
	public String getToken(String username, String password) throws Exception {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new ServletException("Username or Password not filled in");			
		}	
		
		if (null != userAuthenticationService.findByUsernameAndPassword(username, password)) {
			String jwtToken = Jwts.builder()
									.setSubject(username)
									.setIssuedAt(new Date())
									.setExpiration(new Date(System.currentTimeMillis() + 300000))
									.signWith(SignatureAlgorithm.HS256, "secretKey").compact();
			return jwtToken;
		}
		throw new ServletException("Username or Password is Wrong");        
	}
}
