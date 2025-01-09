package com.block.travel.controller;

import com.block.travel.dto.LoginResponse;
import com.block.travel.dto.UserRequest;
import com.block.travel.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody UserRequest userRequest){
        try {
            LoginResponse loginResponse = userService.login(userRequest);
            return ResponseEntity.status(200).body(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }


    @PostMapping("/api/v1/auth/signup")
    public ResponseEntity<Void> signUp(
            @Valid @RequestBody UserRequest userRequest){

        userService.signUp(userRequest);

        return ResponseEntity.status(201).build();

    }
}
