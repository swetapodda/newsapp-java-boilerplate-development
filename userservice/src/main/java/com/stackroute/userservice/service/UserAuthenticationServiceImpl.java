package com.stackroute.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserAutheticationRepository;


@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	private UserAutheticationRepository userAuthenticationRepository;
	
	
	@Autowired
	public UserAuthenticationServiceImpl(UserAutheticationRepository userAuthenticationRepository) {
		this.userAuthenticationRepository = userAuthenticationRepository;
	}
	

	
    @Override
    public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
    	return userAuthenticationRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean saveUser(User user) throws UserAlreadyExistsException {
    	try {
    		List<User> usersList = userAuthenticationRepository.findByUsername(user.getUsername());
    		if (null == usersList || usersList.size() == 0) {
    			userAuthenticationRepository.save(user);
    			return true;
			}
    		throw new UserAlreadyExistsException("User Alreay Registered");		
		} catch (Exception e) {
			throw new UserAlreadyExistsException("User Alreay Registered");
		}
    }
}
