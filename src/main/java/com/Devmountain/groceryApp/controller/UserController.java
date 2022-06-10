package com.Devmountain.groceryApp.controller;

import com.Devmountain.groceryApp.dto.UserDto;
import com.Devmountain.groceryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController { //Controller is the REST Caller GET POST PUT
    @Autowired
    private UserService userService; //Data Injection - this is an interface object or UserService Variable
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto) {
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        return userService.addUser(userDto); //Return interface of UserService
    } //This is a POST Request
    @PostMapping("/login")
    public List<String> userLogin (@RequestBody UserDto userDto) {
        return userService.userLogin(userDto);
    }
}

