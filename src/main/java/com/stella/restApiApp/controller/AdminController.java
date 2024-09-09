package com.stella.restApiApp.controller;

import com.stella.restApiApp.entity.User;
import com.stella.restApiApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/allUsers")
    public ResponseEntity getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        if(allUsers!=null && !allUsers.isEmpty()){
            return new ResponseEntity(allUsers, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/createAdmingUser")
    public void createAdminUser(@RequestBody User user){
        userService.saveAdmin(user);
    }

}