package com.search.elasticsearch.service;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.search.elasticsearch.dao.UserRepository;
import com.search.elasticsearch.model.SearchResults;
import com.search.elasticsearch.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userDao;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void getAllUsers() {
        Iterable<User> users = new ArrayIterator<User>(new User[]{});
        Mockito.when(userDao.findAll()).thenReturn(users);
        Iterable<User> response = userService.getAllUsers();
        Assert.assertEquals(false, response.iterator().hasNext());

    }


    @Test
    public void getUser() {
        User user = new User();
        Mockito.when(userDao.findById(Mockito.anyString())).thenReturn(Optional.of(user));
        User response = userService.getUser("sdsd");
        Assert.assertNotNull(response);
    }

    @Test
    public void addNewUsers() {

        User user = new User();
        Mockito.when(userDao.save(Mockito.any())).thenReturn(user);
        User response = userService.addNewUsers(user);
        Assert.assertNotNull(response);
    }

    @Test
    public void updateById() {
        User user = new User();
        Mockito.when(userDao.save(Mockito.any())).thenReturn(user);
        User response = userService.updateById("sdsd",user);
        Assert.assertNotNull(response);
    }

    @Test
    public void deleteById() {

        User user = new User();
        Mockito.doNothing().when(userDao).delete(Mockito.any());
        userService.deleteById("sdsd");
        Mockito.verify(userDao, Mockito.atLeast(1)).deleteById(Mockito.anyString());
    }

    @Test
    public void search() {

        Page<User> userPage = new AggregatedPageImpl<User>(new ArrayList<>());
        Mockito.when(userDao.search(Mockito.any(), Mockito.any())).thenReturn(userPage);
        SearchResults<User> usersResponse =  userService.search("sdsd", 1, "sd");
        Mockito.verify(userDao, Mockito.atLeast(1)).search(Mockito.any(), Mockito.any());
        Assert.assertNotNull(usersResponse);

    }

}