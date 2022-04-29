package com.example.souq.Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article  {

    private String author;

    private String urlToImage;

    private String publishedAt;

    String url;



    public String getAuthor() {
        return author;
    }

    public String getUrlToImage() {
        return urlToImage;
    }


    public String getPublishedAt() {
        return publishedAt;
    }

    public String getUrl() {
        return url;
    }
}
