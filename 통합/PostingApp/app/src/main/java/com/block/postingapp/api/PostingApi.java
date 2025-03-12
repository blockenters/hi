package com.block.postingapp.api;

import com.block.postingapp.model.PostingListRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
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

}








