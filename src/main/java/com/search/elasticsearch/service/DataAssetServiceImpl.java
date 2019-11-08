package com.search.elasticsearch.service;

import com.search.elasticsearch.dao.DataAssetRepository;
import com.search.elasticsearch.model.DataAsset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DataAssetServiceImpl implements DataAssetService {

    @Autowired
    private DataAssetRepository dataAssetDao;
    @Override
    public Iterable<DataAsset> getAll() {
        return dataAssetDao.findAll();
    }

    @Override
    public DataAsset getDataAsset(String id) {
        return dataAssetDao.findById(id).get();
    }

    @Override
    public DataAsset addNew(DataAsset dataAsset) {
        return dataAssetDao.save(dataAsset);
    }

    @Override
    public DataAsset updateById(String id, DataAsset user) {
        return dataAssetDao.save(user);
    }

    @Override
    public void deleteById(String id) {
        dataAssetDao.deleteById(id);
    }

    @Override
    public Page<DataAsset> getDataAssetByName(String name, String value) {
        Pageable pageable = PageRequest.of(0, 100, Sort.unsorted());
        return dataAssetDao.getDataAssetByName(name, value, pageable);
    }
}
