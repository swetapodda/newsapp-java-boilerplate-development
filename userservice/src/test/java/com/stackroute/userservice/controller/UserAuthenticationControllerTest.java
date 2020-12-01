package com.stackroute.userservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserAuthenticationService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserAuthenticationControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserAuthenticationService userAuthenticationService;

    private User user;

    @InjectMocks
    private UserAuthenticationController authenticationController;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();

        user = new User();
        user.setUsername("Jhon123");
        user.setFirstName("Jhon123");
        user.setLastName("Smith");
        user.setPassword("123456");
    }

    @Test
    public void testRegisterUserSucess() throws Exception {
        Mockito.when(userAuthenticationService.saveUser(user)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable()).andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void testRegisterUserNegative() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(new User())))
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable()).andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void testLoginUserSuccess() throws Exception {
        String userId = "Jhon123";
        String password = "123456";

        Mockito.when(userAuthenticationService.findByUsernameAndPassword(userId, password)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/validate").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void testLoginUserFail() throws Exception {
        String userId = "Jhon123";
        String password = "123456";

        Mockito.when(userAuthenticationService.findByUsernameAndPassword(userId, password)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/validate").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isNotFound()).andDo(MockMvcResultHandlers.print());
    }

    // Parsing String format data into JSON format
    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result;
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "Json processing error";
        }
        return result;
    }

	
}
