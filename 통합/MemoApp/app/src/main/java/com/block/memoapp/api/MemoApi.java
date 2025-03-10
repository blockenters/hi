package com.block.memoapp.api;

import com.block.memoapp.model.Memo;
import com.block.memoapp.model.MemoListRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MemoApi {

    // 메모 리스트 API
    @GET("/memo")
    Call<MemoListRes> getMemoList(@Header("Authorization") String token,
                                  @Query("page") int page,
                                  @Query("size") int size);
    // 메모 생성 API
    @POST("/memo")
    Call<Void> createMemo(@Header("Authorization") String token, @Body Memo memo);

    // 메모 수정 API
    @POST("/memo/{memoId}")
    Call<Void> updateMemo(@Path("memoId") long memoId,
               @Header("Authorization") String token,
               @Body Memo memo);

    // 메모 삭제 API
    @DELETE("/memo/{memoId}")
    Call<Void> deleteMemo(@Path("memoId") long memoId, @Header("Authorization") String token);

}








