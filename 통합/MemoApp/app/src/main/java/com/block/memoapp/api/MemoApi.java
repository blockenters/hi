package com.block.memoapp.api;

import com.block.memoapp.model.MemoListRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MemoApi {

    // 메모 리스트 API
    @GET("/memo")
    Call<MemoListRes> getMemoList(@Header("Authorization") String token,
                                  @Query("page") int page,
                                  @Query("size") int size);

}
