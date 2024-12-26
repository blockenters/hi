package com.block.jwt.controller;

import com.block.jwt.dto.UserRequest;
import com.block.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/users/signup")
    public ResponseEntity<Object> signUp(@RequestBody UserRequest userRequest) {

        int result = userService.signUp(userRequest);

        if(result == 1 || result == 2){
            return ResponseEntity.status(400).build();
        } else if(result == 3){
            return ResponseEntity.status(409).build();
        }

        return ResponseEntity.status(201).build();

    }
}
