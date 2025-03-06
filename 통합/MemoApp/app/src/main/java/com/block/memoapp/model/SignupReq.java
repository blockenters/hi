package com.block.memoapp.model;

public class SignupReq {
    public String email;
    public String password;
    public String nickname;

    public SignupReq() {
    }

    public SignupReq(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
