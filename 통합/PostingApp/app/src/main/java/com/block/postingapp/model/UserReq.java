package com.block.postingapp.model;

public class UserReq {
    public String email;
    public String password;

    public UserReq() {
    }

    public UserReq(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
