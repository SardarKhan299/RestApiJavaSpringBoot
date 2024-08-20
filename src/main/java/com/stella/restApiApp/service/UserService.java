package com.stella.restApiApp.service;

import com.stella.restApiApp.entity.JournalEntity;
import com.stella.restApiApp.entity.User;
import com.stella.restApiApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    UserRepo userRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepo.save(user);
        return user;
    }

    public User saveNewUser(User user){
        userRepo.save(user);
        return user;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepo.findById(id);
    }

    public void deleteUserById(ObjectId id){
        userRepo.deleteById(id);
    }


    public User findByUserName(String username){
        return userRepo.findByUserName(username);
    }
}
