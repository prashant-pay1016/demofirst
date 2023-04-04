package com.restexample.restdemo.service;

import com.restexample.restdemo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ServiceUser {

    User saveUser(User user);

//    void saveUser1(User user);

    List<User> getAllUser();
    User getUserById(Long id);
    User updateUserById(User user, Long id);
    void deleteUser(Long id);

}
