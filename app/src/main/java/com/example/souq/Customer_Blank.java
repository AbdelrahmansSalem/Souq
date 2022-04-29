package com.example.souq;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.souq.Api.Article;
import com.example.souq.Api.GetData;
import com.example.souq.Api.News;

import com.example.souq.databinding.FragmentCustomerBlankBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Customer_Blank extends Fragment {

    FragmentCustomerBlankBinding binding;

    ArrayList<String> authors;
    ArrayList<String> publishedAt;
    ArrayList<String> url;
    ArrayList<String> urlToImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentCustomerBlankBinding.inflate(inflater,container,false);
        View view =binding.getRoot();


        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetData data=retrofit.create(GetData.class);
        Call<News> call=data.getNews("4ab37cc11d98488abfc210fd9800598f");

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (!response.isSuccessful()) {
                    Log.i("error", response.message());

                }

                authors=new ArrayList<>();
                publishedAt=new ArrayList<>();
                url=new ArrayList<>();
                urlToImage =new ArrayList<>();
                News techcrunch=response.body();
                ArrayList<Article> articles=techcrunch.getArticles();
                for (Article article :articles){

                    authors.add(article.getAuthor());
                    publishedAt.add(article.getPublishedAt());
                    urlToImage.add(article.getUrlToImage());
                    url.add(article.getUrl());
                }
                API_List api_list =new API_List(authors,publishedAt,url,urlToImage,getContext(),position -> {
                    Uri uri = Uri.parse(url.get(position)); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                });
                binding.APiList.setAdapter(api_list);
                binding.APiList.setLayoutManager(new LinearLayoutManager(getContext()));
            }
            @Override
            public void onFailure(Call<News> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}