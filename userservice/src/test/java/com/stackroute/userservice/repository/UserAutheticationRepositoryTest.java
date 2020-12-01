package com.stackroute.userservice.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userservice.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserAutheticationRepositoryTest {

    @Autowired
    private UserAutheticationRepository autheticationRepository;


    private User user;


    @Before
    public void setUp() throws Exception {
    	user = new User();
        user.setUsername("Jhon123");
        user.setFirstName("Jhon123");
        user.setLastName("Smith");
        user.setPassword("123456");
    }

    @After
    public void tearDown() throws Exception {
        autheticationRepository.deleteAll();
    }

    @Test
    public void testRegisterUserSuccess() {
    	Assert.assertNotNull(autheticationRepository.save(user));        
    }
    
    @Test
    public void testFindByUsernameAndPasswordSuccess() {
        autheticationRepository.save(user);
        Assert.assertNotNull(autheticationRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()));
    }
    
    @Test
    public void testFindByUsernameAndPasswordNegative() {
        autheticationRepository.save(user);
        Assert.assertNull(autheticationRepository.findByUsernameAndPassword("Test", "Test"));
    }

    @Test
    public void testFindByUsername() {
        autheticationRepository.save(user);
        Assert.assertNotNull(autheticationRepository.findByUsername(user.getUsername()));
    }
    
    @Test
    public void testFindByUsernameNegative() {
        autheticationRepository.save(user);
        Assert.assertEquals(0, autheticationRepository.findByUsername("test").size());
    }

}
