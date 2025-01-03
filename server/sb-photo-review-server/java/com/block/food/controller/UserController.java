package com.block.food.controller;

import com.block.food.dto.UserLoginResponse;
import com.block.food.dto.UserRequest;
import com.block.food.service.UserService;
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
    public ResponseEntity<Object> signUp(@RequestBody UserRequest userRequest) {

        System.out.println(userRequest.email);

        int result = userService.signUp(userRequest);

        if(result != 0){
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(201).build();
    }


    @PostMapping("/api/v1/users/login")
    public ResponseEntity<UserLoginResponse> userLogin(@RequestBody UserRequest userRequest) {
        // 로그인 로직
        Object result = userService.userLogin(userRequest);

        if(result instanceof Integer){
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(200).body(new UserLoginResponse((String)result));
    }
}
