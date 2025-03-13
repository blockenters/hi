package com.block.postingapp.api;

import com.block.postingapp.model.PostingListRes;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostingApi {

    @GET("/posting")
    Call<PostingListRes> getPostingList(@Header("Authorization") String token,
                                        @Query("page") int page,
                                        @Query("size") int size);

    @POST("/posting/{postingId}/{status}")
    Call<Void> setLike(@Header("Authorization") String token,
                        @Path("postingId") long postingId,
                        @Path("status") String status);


    // 포스팅 생성 API
    @Multipart
    @POST("/posting")
    Call<Void> addPosting(@Header("Authorization") String token,
                           @Part MultipartBody.Part image,
                           @Part("content") RequestBody content);


}








