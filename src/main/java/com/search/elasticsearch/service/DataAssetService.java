package com.search.elasticsearch.service;


import com.search.elasticsearch.model.DataAsset;
import org.springframework.data.domain.Page;

public interface DataAssetService {

    Iterable<DataAsset> getAll();
    DataAsset getDataAsset(String id);
    DataAsset addNew(DataAsset dataAsset);
    DataAsset updateById(String id, DataAsset user);

    void deleteById(String id);

    Page<DataAsset> getDataAssetByName(String name, String value);
}

