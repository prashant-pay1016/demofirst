package com.restexample.restdemo.controller;

import com.restexample.restdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    List<User> allUser = new ArrayList<>();



    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        allUser.add(user);
        return ResponseEntity.ok(user);
    }

    //    @GetMapping(value="/test")
//    public List<User> allUsers(){
//        return allUser;
//    }
    @RequestMapping(value = "/fetch-user", method = RequestMethod.GET)
    public ResponseEntity<?> fetchAllUser() {
        Integer customHttpStatusValue = 406;
        if (!allUser.isEmpty()) {
            return ResponseEntity.ok(allUser);
        } else {
            return ResponseEntity.status(customHttpStatusValue).body("No user found! First insert an user");
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No user found! First insert an user");
        }
    }

    @RequestMapping(value = "/fetch-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> fetchById(@PathVariable("id") Integer id) {
        User obj = allUser.stream().filter(user -> id.equals(user.getId())).findFirst().orElse(null);
          Integer customHttpStatusValue = 499;
        if (obj != null) {
            return ResponseEntity.ok(obj);
        } else {
            return ResponseEntity.status(customHttpStatusValue).body("No user found by this id: "+id);
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User doesn't exist in table!");
        }
    }


    @RequestMapping(value = "/delete-user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Integer id) {
        boolean removed = allUser.removeIf(user -> user.getId() == id);
        if (removed) {
            return ResponseEntity.ok("Customer with ID " + id + " removed.");
        } else {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found!");
        }
    }

    @RequestMapping(value = "/update-user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateById(@PathVariable("id") Integer id, @RequestBody User user) {

        User user1 = allUser.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
        if (user1 != null) {
            user1.setName(user.getName());
            user1.setAge(user.getAge());
            user1.setAddress(user.getAddress());
            user1.setMobileNumber(user.getMobileNumber());
            user1.setEducation(user.getEducation());
            return ResponseEntity.ok(user1);
        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found! Please check the id again");
        }


    }


    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
         String fileName =  file.getOriginalFilename();
         file.transferTo(new File("/home/parshant/Desktop/system_Image/" + file.getOriginalFilename()));
         return ResponseEntity.ok(fileName);

    }

}
