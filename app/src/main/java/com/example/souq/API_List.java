package com.example.souq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.souq.databinding.ApiListBinding;

import java.util.ArrayList;
import java.util.List;

public class API_List extends RecyclerView.Adapter<API_List.MyViewHolder> {

    ArrayList<String> title;
    ArrayList<String> publishedAt;
    ArrayList<String> url;
    ArrayList<String> urlToImage;
    Context context;
    Api_url api_url;

    public API_List(ArrayList<String> title, ArrayList<String> publishedAt, ArrayList<String> url, ArrayList<String> urlToImage, Context context, Api_url api_url) {
        this.title = title;
        this.publishedAt = publishedAt;
        this.url = url;
        this.urlToImage = urlToImage;
        this.context = context;
        this.api_url = api_url;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.api_list,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.author.setText(title.get(position));
        holder.publat.setText(publishedAt.get(position));
        Glide.with(context).load(urlToImage.get(position)).into(holder.urlimage);
    }

    @Override
    public int getItemCount() {
        return urlToImage.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView author;
        TextView publat;
        ImageView urlimage;
        TextView url;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             author =itemView.findViewById(R.id.author);
             publat =itemView.findViewById(R.id.publishedat);
             urlimage =itemView.findViewById(R.id.api_image);
             url =itemView.findViewById(R.id.url);
             url.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     api_url.onclick(getAdapterPosition());
                 }
             });
        }
    }
}
