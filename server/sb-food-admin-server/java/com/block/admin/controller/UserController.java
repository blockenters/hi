package com.block.admin.controller;

import com.block.admin.dto.UserLoginResponse;
import com.block.admin.dto.UserRequest;
import com.block.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/v1/admin/signup")
    public ResponseEntity<Object> signUp(@RequestBody UserRequest userRequest){
        int result = userService.signUp(userRequest);
        if (result != 0){
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/api/v1/admin/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserRequest userRequest){
        Object result = userService.login(userRequest);
        if (result instanceof Integer){
            return ResponseEntity.status(400).build();
        }else{
            return ResponseEntity.status(200).body(new UserLoginResponse((String) result));
        }
    }
}
