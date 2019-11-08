package com.search.elasticsearch.dao;

import com.search.elasticsearch.model.DataAsset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DataAssetRepository extends ElasticsearchRepository<DataAsset, String> {

    Page<DataAsset> findByName(String name, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
    Page<DataAsset> findByNameUsingCustomQuery(String name, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"?0\": \"?1\"}}]}}")
    Page<DataAsset> getDataAssetByName(String name, String value, Pageable pageable);
}
