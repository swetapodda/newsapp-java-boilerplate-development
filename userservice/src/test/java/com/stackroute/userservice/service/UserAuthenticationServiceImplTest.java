package com.stackroute.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.model.User;

import com.stackroute.userservice.repository.UserAutheticationRepository;

public class UserAuthenticationServiceImplTest {

    @Mock
    private UserAutheticationRepository autheticationRepository;


    private User user;
    @InjectMocks
    private UserAuthenticationServiceImpl authenticationService;

    Optional<User> optional;
    List<User> list;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUsername("Jhon123");
        user.setFirstName("Jhon123");
        user.setLastName("Smith");
        user.setPassword("123456");
        list = new ArrayList<User>();
        list.add(user);
        
        optional = Optional.of(user);
    }

    @Test
    public void testSaveUserSuccess() throws Exception {
    	user = new User();
        user.setUsername("admin123");
        user.setFirstName("admin123");
        user.setLastName("Smith");
        user.setPassword("123456");
    	Mockito.when(autheticationRepository.findByUsername(user.getUsername())).thenReturn(null);
        boolean flag = authenticationService.saveUser(user);
        Assert.assertTrue(flag);
    }
    
    @Test(expected = UserAlreadyExistsException.class)
    public void testSaveUseNegative() throws Exception {
    	Mockito.when(autheticationRepository.findByUsername(user.getUsername())).thenReturn(list);
        boolean flag = authenticationService.saveUser(user);
        Assert.assertEquals("User Alreay Registered", true, flag);
        Assert.assertTrue(flag);
    }

    @Test
    public void testfindByUsernameAndPassword() throws Exception {
        Mockito.when(autheticationRepository.findByUsernameAndPassword("Jhon123", "123456")).thenReturn(user);
        Assert.assertNotNull(authenticationService.findByUsernameAndPassword("Jhon123", "123456"));
    }
    
    @Test
    public void testfindByUsernameAndNegative() throws Exception {
        Mockito.when(autheticationRepository.findByUsernameAndPassword("Jhon123", "123456")).thenReturn(null);
        Assert.assertNull(authenticationService.findByUsernameAndPassword("Jhon123", "123456"));
    }
}
