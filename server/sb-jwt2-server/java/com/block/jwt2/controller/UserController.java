package com.block.jwt2.controller;

import com.block.jwt2.dto.UserLoginResponse;
import com.block.jwt2.dto.UserRequest;
import com.block.jwt2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @PostMapping("/api/users/login")
    public ResponseEntity<UserLoginResponse> userLogin(@RequestBody UserRequest userRequest){

        System.out.println("Login userRequest.email : " + userRequest.email);

        Object result = userService.userLogin(userRequest);
        if (result instanceof Integer){
            if((Integer)result == 1 || (Integer)result == 2){
                return ResponseEntity.status(400).build();
            }else if((Integer)result == 3){
                return ResponseEntity.status(401).build();
            }
        }
        return ResponseEntity.status(200)
                .body(new UserLoginResponse((String)result));
    }

    @PostMapping("/api/users/logout")
    public ResponseEntity<Object> userLogout(@RequestHeader("Authorization") String token){

        return ResponseEntity.status(200).build();

    }
}
