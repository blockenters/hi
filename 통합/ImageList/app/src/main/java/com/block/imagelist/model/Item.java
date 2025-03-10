package com.block.imagelist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
    public Long id;
    public String author;
    public String url;

    @SerializedName("download_url")
    @Expose
    public String downloadUrl;
}
