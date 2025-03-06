package com.block.simplememo.api;


import com.block.simplememo.model.MemoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

// 레트로핏 라이브러리 사용법
// 1. 인터페이스로 만든다.
public interface MemoApi {

    @GET("/posting.json")
    Call<MemoResponse> getMemoList();

}
