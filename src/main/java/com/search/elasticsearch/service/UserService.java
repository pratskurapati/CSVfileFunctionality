package com.search.elasticsearch.service;


import com.search.elasticsearch.model.SearchResults;
import com.search.elasticsearch.model.User;

public interface UserService {

    Iterable<User> getAllUsers();
    User getUser(String userId);
    User addNewUsers(User user);

    User updateById(String id, User user);

    void deleteById(String id);

    SearchResults<User> search(String query_term, Integer page_index, String index);
}
