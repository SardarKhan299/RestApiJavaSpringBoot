package com.stella.restApiApp.controller;

import com.stella.restApiApp.entity.User;
import com.stella.restApiApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/healthCheck")
    public  String checkHealth(){
        return "Ok";
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

}
