package com.search.elasticsearch.service;

import com.search.elasticsearch.dao.DataAssetRepository;
import com.search.elasticsearch.model.DataAsset;
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

@RunWith(SpringJUnit4ClassRunner.class)
public class DataAssetServiceImplTest {

    @Mock
    private DataAssetRepository dataAssetDao;

    @InjectMocks
    private DataAssetServiceImpl dataAssetService;


    @Test
    public void testGetDataAssetByName(){
        Page<DataAsset> userPage = new AggregatedPageImpl<DataAsset>(new ArrayList<>());

        Mockito.when(dataAssetDao.getDataAssetByName(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(userPage);
        Page<DataAsset> usersResponse =  dataAssetService.getDataAssetByName("sdsd", "sd");
        Mockito.verify(dataAssetDao, Mockito.atLeast(1)).getDataAssetByName(Mockito.any(), Mockito.any(), Mockito.any());
        Assert.assertNotNull(usersResponse);
    }

}