package com.block.imagelist.api;

import com.block.imagelist.model.ItemListRes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemApi {

    @GET("/test_list.json")
    Call<ItemListRes> getItemList();

}
