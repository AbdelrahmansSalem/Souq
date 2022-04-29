package com.example.souq;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Seller_product_list extends RecyclerView.Adapter<Seller_product_list.Viewholder> {

    ArrayList<String> product_name;
    ArrayList<String> product_price;
    Context context;

    public Seller_product_list(ArrayList<String> product_name, ArrayList<String> product_price, Context context) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.customer_product_list,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String n=product_name.get(position);
        String t=product_price.get(position);
        holder.name.setText(n);
        holder.price.setText(t);
    }

    @Override
    public int getItemCount() {

        return product_name.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_product);
            price=itemView.findViewById(R.id.price_product);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return false;
                }
            });

        }
    }
}
