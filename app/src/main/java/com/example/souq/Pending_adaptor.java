package com.example.souq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Pending_adaptor extends RecyclerView.Adapter<Pending_adaptor.Myviewholder> {

ArrayList<String> sellers;
Context context;
Show_seller_info sellerInfo;
    public Pending_adaptor(ArrayList<String> sellers, Context context ,Show_seller_info sellerInfo) {
        this.sellers = sellers;
        this.context = context;
        this.sellerInfo=sellerInfo;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.pending_list,parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        String name=sellers.get(position);
        holder.name.setText(name);

    }

    @Override
    public int getItemCount() {
        return sellers.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView name;
        Button showinfo;

        public Myviewholder(@NonNull View view) {
            super(view);
            name=view.findViewById(R.id.username);
            showinfo=view.findViewById(R.id.showinfo);
            showinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    sellerInfo.showinfo(getAdapterPosition());
                }
            });
        }
    }
}
