package com.block.postingapp.api;

import com.block.postingapp.model.LoginRes;
import com.block.postingapp.model.UserReq;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("/auth/user/signup")
    Call<Void> signup(@Body UserReq userReq);

    @POST("/auth/user/login")
    Call<LoginRes> login(@Body UserReq userReq);
}











