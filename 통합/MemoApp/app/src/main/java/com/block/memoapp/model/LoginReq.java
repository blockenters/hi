package com.block.memoapp.model;

public class LoginReq {
    public String email;
    public String password;

    public LoginReq() {
    }

    public LoginReq(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
