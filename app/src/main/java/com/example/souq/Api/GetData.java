package com.example.souq.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetData {
    @GET("v2/top-headlines?sources=techcrunch")
    Call<News> getNews(@Query("apiKey") String apiKey);
}
