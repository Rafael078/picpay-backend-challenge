package com.desafiobackendpicpay.controllers;

import com.desafiobackendpicpay.domain.user.User;
import com.desafiobackendpicpay.dtos.UserDTO;
import com.desafiobackendpicpay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;
    private UserService UserService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
       List<User> users = this.UserService.getAllUsers();
       return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
