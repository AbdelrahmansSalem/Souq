package com.example.souq.Api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class News {

    private String status;
    ArrayList<Article> articles;

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
