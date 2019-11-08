package com.search.elasticsearch.dao;

import com.search.elasticsearch.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User, String> {

    Page<User> findByName(String name, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
    Page<User> findByNameUsingCustomQuery(String name, Pageable pageable);
}
