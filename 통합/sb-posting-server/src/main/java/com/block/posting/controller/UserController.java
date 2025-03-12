package com.block.posting.controller;

import com.block.posting.dto.LoginRes;
import com.block.posting.dto.SignUpReq;
import com.block.posting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/auth/user/login")
    public ResponseEntity<? extends Object> login(@RequestBody SignUpReq signUpReq){

        try {
            LoginRes loginRes = userService.login(signUpReq);
            return ResponseEntity.status(200).body(loginRes);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }


    @PostMapping("/auth/user/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpReq signUpReq){
        try {
            userService.signUp(signUpReq);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

}
