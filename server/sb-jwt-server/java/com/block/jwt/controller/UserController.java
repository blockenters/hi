package com.block.jwt.controller;

import com.block.jwt.dto.UserRequest;
import com.block.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/users/signup")
    signUp(@RequestBody UserRequest userRequest) {

        userService.signUp(userRequest);

    }
}
