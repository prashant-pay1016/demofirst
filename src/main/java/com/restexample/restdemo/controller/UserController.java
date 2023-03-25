package com.restexample.restdemo.controller;

import com.restexample.restdemo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    List<User> allUser = new ArrayList<>();
    private User user;

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
        if (allUser != null) {
            return ResponseEntity.ok(allUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no user found!");
        }
    }

    @RequestMapping(value = "/fetchid/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> fetchById(@PathVariable("id") Integer id) {
        User obj = allUser.stream().filter(user -> id.equals(user.getId())).findFirst().orElse(null);

        if (obj != null) {
            return ResponseEntity.ok(obj);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found!");
        }
    }


    @RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Integer id) {
        boolean removed = allUser.removeIf(user -> user.getId() == id);
        if (removed) {
            return ResponseEntity.ok("Customer with ID " + id + " removed.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/updateuser/{id}", method = RequestMethod.PUT)
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

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found!");
        }


    }

}
