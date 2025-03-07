package com.block.memoapp.api;

import com.block.memoapp.model.LoginReq;
import com.block.memoapp.model.LoginRes;
import com.block.memoapp.model.SignupReq;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    // 회원가입 API
    @POST("/auth/user/signup")
    Call<Void> signup(@Body SignupReq signupReq);

    // 로그인 API
    @POST("/auth/user/login")
    Call<LoginRes> login(@Body LoginReq loginReq);

}
