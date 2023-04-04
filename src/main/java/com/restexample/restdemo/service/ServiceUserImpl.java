package com.restexample.restdemo.service;

import com.restexample.restdemo.exception.DemoException;
import com.restexample.restdemo.model.User;
import com.restexample.restdemo.repo.CrudRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUserImpl implements ServiceUser{



    @Autowired
    private CrudRepo crudRepo;



    @Override
    public User saveUser(User user) {
        return crudRepo.save(user);
    }

//    @Override
//    public void saveUser1(User user) {
//        crudRepo.save(user);
//    }


    @Override
    public List<User> getAllUser() {

        return crudRepo.findAll();


    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = crudRepo.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }

    }

    @Override
    public User updateUserById(User user, Long id) {
        User user1 = crudRepo.findById(id).orElseThrow(() ->
                new DemoException("User", "ID", id));
        {
            user1.setName(user.getName());
            user1.setAge(user.getAge());
            user1.setAddress(user.getAddress());
            user1.setMobileNumber(user.getMobileNumber());
            user1.setEducation(user.getEducation());
            crudRepo.save(user1);
            return user1;

        }
    }

    @Override
    public void deleteUser(Long id) {
        crudRepo.findById(id).orElseThrow(() -> new DemoException("User", "ID", id));


        crudRepo.deleteById(id);

    }
}
