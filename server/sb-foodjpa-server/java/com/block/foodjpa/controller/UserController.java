package com.block.foodjpa.controller;

import com.block.foodjpa.dto.UserRequest;
import com.block.foodjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/api/v1/users/signup")
    public ResponseEntity<Object> signUp(@RequestBody UserRequest userRequest){
        try {
            userService.signUp(userRequest);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
