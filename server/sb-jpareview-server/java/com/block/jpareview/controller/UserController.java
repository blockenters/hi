package com.block.jpareview.controller;

import com.block.jpareview.dto.LoginResponse;
import com.block.jpareview.dto.UserRequest;
import com.block.jpareview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest userRequest){

        try {
            LoginResponse loginResponse = userService.login(userRequest);
            return ResponseEntity.status(200).body(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }

    }


    @PostMapping("/api/auth/signup")
    public ResponseEntity<Void> signUp(@RequestBody UserRequest userRequest){
        try {
            userService.signUp(userRequest);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}





