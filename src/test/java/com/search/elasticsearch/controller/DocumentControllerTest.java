package com.search.elasticsearch.controller;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.search.elasticsearch.model.User;
import com.search.elasticsearch.service.DataAssetService;
import com.search.elasticsearch.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

@RunWith(SpringJUnit4ClassRunner.class)
public class DocumentControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private DataAssetService dataAssetService;

    @InjectMocks
    private DocumentController documentController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(documentController).build();
    }

    @Test
    public void getAllUsers() throws Exception {

        Iterable<User> users = new ArrayIterator<User>(new User[]{});
        Mockito.when(userService.getAllUsers()).thenReturn(users);
        Iterable<User> response = documentController.getAllUsers();
        Assert.assertEquals(false, response.iterator().hasNext());

    }

    @Test
    public void getUser()throws Exception {
        User user = new User();
        Mockito.when(userService.getUser(Mockito.anyString())).thenReturn(user);
        User response = documentController.getUser("123132");
        Assert.assertNotNull(response);
    }

    @Test
    public void addNewUsers() throws Exception {
        User user = new User();
        Mockito.when(userService.addNewUsers(Mockito.any(User.class))).thenReturn(user);
        User response = documentController.addNewUsers(user);
        Assert.assertNotNull(response);

    }

    @Test
    public void addNewUsersFromCSV() throws Exception {
        User user = new User();
        ClassLoader classLoader = getClass().getClassLoader();
        MultipartFile multipartFile = new MockMultipartFile("sdsd",
                Files.readAllBytes(new File(classLoader.getResource("csv/user.csv").getFile()).toPath()));
        Mockito.when(userService.addNewUsers(Mockito.any(User.class))).thenReturn(user);
        Boolean response = documentController.addNewUsersFromCSV(multipartFile);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.booleanValue());

    }

    @Test
    public void update() throws Exception {
        User user = new User();
        Mockito.when(userService.updateById(Mockito.anyString(), Mockito.any(User.class))).thenReturn(user);
        User response = documentController.update(user);
        Assert.assertNotNull(response);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(userService).deleteById(Mockito.anyString());
        Boolean response = documentController.delete("123132");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.booleanValue());
    }

    @Test
    public void getAllTable() {
    }

    @Test
    public void getTable() {
    }

    @Test
    public void addNewTable() {
    }

    @Test
    public void addNewTablesFromCSV() {
    }

    @Test
    public void updateTable() {
    }

    @Test
    public void deleteTable() {
    }
}