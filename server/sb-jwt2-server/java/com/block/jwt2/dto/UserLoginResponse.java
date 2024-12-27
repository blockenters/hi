package com.block.jwt2.dto;

public class UserLoginResponse {
    public String token;

    public UserLoginResponse() {
    }

    public UserLoginResponse(String token) {
        this.token = token;
    }
}
