package com.search.elasticsearch.controller;

import com.search.elasticsearch.model.SearchResults;
import com.search.elasticsearch.model.User;
import com.search.elasticsearch.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {


    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    public void search() throws Exception {
        User user = new User();
        SearchResults<User> searchResults = new SearchResults<>();
        Mockito.when(userService.search(Mockito.any(), Mockito.anyInt(), Mockito.any())).thenReturn(searchResults);
        SearchResults<User> response = userController.search("abc", 1, "my_index");
        Assert.assertNotNull(response);

    }
}