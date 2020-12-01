package com.stackroute.userservice.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.stackroute.userservice.model.User;


public interface UserAuthenticationRepositoryCustom {
	

	 User findByUsernameAndPassword(String username, String password);
	 
	 List<User> findByUsername(String username); 
}
