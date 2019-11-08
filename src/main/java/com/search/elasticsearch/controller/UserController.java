package com.search.elasticsearch.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.search.elasticsearch.model.SearchResults;
import com.search.elasticsearch.model.User;
import com.search.elasticsearch.service.UserService;
import com.search.elasticsearch.util.CsvUtils;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public SearchResults<User> search(@RequestParam(required = false) String query_term,
                                      @RequestParam(required = false) Integer page_index,
                                      @RequestParam(required = false) String index) throws Exception {
        return userService.search(query_term, page_index, index);
    }
    
    
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public List<User> save() throws IOException{
    	Resource resource = new ClassPathResource("csv/user.csv");
    	InputStream stream = resource.getInputStream();
    	//InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("csv/user.csv");
    	List<User> users = CsvUtils.read(User.class, stream);
    	users.forEach(u->{
            userService.addNewUsers(u);
        });
        return users;
    }
    
    
    
    
    
    
    
    

}