package com.block.jwt.dto;

public class UserLoginResponse {
    public String token;

    public UserLoginResponse() {
    }

    public UserLoginResponse(String token) {
        this.token = token;
    }
}
