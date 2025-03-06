package com.block.memoapp.api;

import com.block.memoapp.model.SignupReq;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("/auth/user/signup")
    Call<Void> signup(@Body SignupReq signupReq);

}
