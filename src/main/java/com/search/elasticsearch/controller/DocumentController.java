package com.search.elasticsearch.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.search.elasticsearch.model.DataAsset;
import com.search.elasticsearch.model.User;
import com.search.elasticsearch.service.DataAssetService;
import com.search.elasticsearch.service.UserService;
import com.search.elasticsearch.util.CsvUtils;

@RestController
@RequestMapping("/documents")
public class DocumentController extends BaseController{

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataAssetService dataAssetService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public Iterable<User> getAllUsers() throws Exception {
        return userService.getAllUsers();
    }

    @RequestMapping(value="/user/id/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) throws Exception {
        User user = userService.getUser(userId);
        return user;
    }

    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    public User addNewUsers(@RequestBody User user) throws Exception{
        userService.addNewUsers(user);
        return user;
    }

    @RequestMapping(value = "/user/new/csv", method = RequestMethod.POST, consumes = "multipart/form-data")
    public Boolean addNewUsersFromCSV(@RequestParam("file") MultipartFile file) throws Exception{
        List<User> users = CsvUtils.read(User.class, file.getInputStream());
        users.forEach(u->{
            userService.addNewUsers(u);
        });

        return true;
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    public User update(@RequestBody User user) throws Exception{
        userService.updateById(user.getUserId(),user);
        return user;
    }

    @RequestMapping(value = "/user/delete/{userId}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable String userId) throws Exception{
        userService.deleteById(userId);
        return true;
    }

    @RequestMapping(value = "/table/all", method = RequestMethod.GET)
    public Iterable<DataAsset> getAll() {
        return dataAssetService.getAll();
    }

    @RequestMapping(value = "/table/id/{dataAssetId}", method = RequestMethod.GET)
    public DataAsset get(@PathVariable String dataAssetId) {
        DataAsset dataAsset = dataAssetService.getDataAsset(dataAssetId);
        return dataAsset;
    }

    @RequestMapping(value = "/table/new", method = RequestMethod.POST)
    public DataAsset addNew(@RequestBody DataAsset dataAsset) {
        dataAssetService.addNew(dataAsset);
        return dataAsset;
    }

    @RequestMapping(value = "/table/new/csv", method = RequestMethod.POST, consumes = "multipart/form-data")
    public Boolean addNewTablesFromCSV(@RequestParam("file") MultipartFile file) throws Exception{
        List<DataAsset> dataAssets = CsvUtils.read(DataAsset.class, file.getInputStream());
        dataAssets.forEach(u->{
            dataAssetService.addNew(u);
        });

        return true;
    }

    @RequestMapping(value = "/table/update", method = RequestMethod.PUT)
    public DataAsset update(@RequestBody DataAsset dataAsset) throws Exception{
        dataAssetService.updateById(dataAsset.getId(),dataAsset);
        return dataAsset;
    }

    @RequestMapping(value = "/table/delete/{id}", method = RequestMethod.DELETE)
    public Boolean deleteTable(@PathVariable String id) throws Exception{
        dataAssetService.deleteById(id);
        return true;
    }


}
