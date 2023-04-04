package com.restexample.restdemo.controller;

import com.restexample.restdemo.model.User;

import com.restexample.restdemo.repo.CrudRepo;
import com.restexample.restdemo.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {



           @Autowired
           private ServiceUser serviceUser;
@Autowired
private CrudRepo crudRepo;
    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {

        serviceUser.saveUser(user);

        return ResponseEntity.ok(user);
    }


    @RequestMapping(value = "/fetch-user", method = RequestMethod.GET)
    public List<User> fetchAllUser() {
      return serviceUser.getAllUser();
    }

    @RequestMapping(value = "/fetch-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> fetchById(@PathVariable("id") Long id) {


        return new ResponseEntity<User>(serviceUser.getUserById(id),HttpStatus.OK);
    }

//
    @RequestMapping(value = "/delete-user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {

        serviceUser.deleteUser(id);
        return new ResponseEntity<String>("user deleted from database",HttpStatus.OK);
    }
//
    @RequestMapping(value = "/update-user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateById(@PathVariable("id") Long id, @RequestBody User user) {


        return new ResponseEntity<User>(serviceUser.updateUserById(user,id),HttpStatus.OK);


    }


}
