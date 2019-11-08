package com.search.elasticsearch.controller;

import com.search.elasticsearch.model.DataAsset;
import com.search.elasticsearch.service.DataAssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static com.search.elasticsearch.controller.DataAssetController.dataAssetUrl;

@RestController
@RequestMapping(dataAssetUrl)
public class DataAssetController extends BaseController{

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    public static final  String dataAssetUrl = "/table" ;
    private static final String findByNameAndValueUrl = "/name/{name}/value/{value}";

    @Autowired
    private DataAssetService dataAssetService;


    @RequestMapping(value = findByNameAndValueUrl,
            method = RequestMethod.GET)
    public Page<DataAsset> get(@PathVariable String name, @PathVariable String value) {
        return  dataAssetService.getDataAssetByName(name,value);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<DataAsset> getAll() {
        return dataAssetService.getAll();
    }



}
