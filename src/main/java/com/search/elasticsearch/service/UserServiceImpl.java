package com.search.elasticsearch.service;

import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.search.elasticsearch.dao.UserRepository;
import com.search.elasticsearch.model.SearchResults;
import com.search.elasticsearch.model.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userDAO;

    @Override
    public Iterable<User> getAllUsers() {
        return  userDAO.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userDAO.findById(userId).get();
    }

    @Override
    public User addNewUsers(User user) {
        return userDAO.save(user);
    }

    @Override
    public User updateById(String id, User user) {
        return userDAO.save(user);
    }

    @Override
    public void deleteById(String id) {
        userDAO.deleteById(id);
    }

    @Override
    public SearchResults<User> search(String query_term, Integer page_index, String index) {
        if(index == null){
            index = "my_index";
        }

        if (query_term == null){
            return new SearchResults<User>();
        }

        if(page_index == null){
            page_index = 0;
        }

        QueryBuilder queryBuilder = new MultiMatchQueryBuilder(query_term, new String[]{"name", "first_name", "last_name", "email"});

        Pageable pageable = PageRequest.of(page_index, 100, Sort.unsorted());

        Page<User> users = userDAO.search(queryBuilder, pageable);
        SearchResults<User> searchResults = new SearchResults<>(users.getTotalPages(), users.toList());

        return searchResults;
    }
}
