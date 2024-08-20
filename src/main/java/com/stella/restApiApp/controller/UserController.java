package com.stella.restApiApp.controller;

import com.stella.restApiApp.entity.User;
import com.stella.restApiApp.repository.UserRepo;
import com.stella.restApiApp.service.JournalEntryService;
import com.stella.restApiApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepo userRepo;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }



    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUserName(username);
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveUser(userInDb);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUserName(authentication.getName());
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
