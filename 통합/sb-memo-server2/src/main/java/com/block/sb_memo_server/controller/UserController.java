package com.block.sb_memo_server.controller;

import com.block.sb_memo_server.dto.SignUpRequest;
import com.block.sb_memo_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/auth/user/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest ){
        try{
            userService.signUp(signUpRequest);
        } catch(Exception e){
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(200).build();
    }
}
