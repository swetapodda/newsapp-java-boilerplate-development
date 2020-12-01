package com.stackroute.userservice.repository;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userservice.model.User;

@Repository
@ComponentScan("com.stackroute.userservice.model")
public interface UserAutheticationRepository extends JpaRepository<User, String> {
    
 User findByUsernameAndPassword(String username, String password);
	 
	 List<User> findByUsername(String username); 
	 
}
