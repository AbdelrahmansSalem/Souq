package com.example.souq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Customer_product_list extends RecyclerView.Adapter<Customer_product_list.MyViewHolder> {

    ArrayList<String> product_name;
    ArrayList<String> product_price;
    Context context;
    Customer_onclick onclick;

    public Customer_product_list(ArrayList<String> product_name, ArrayList<String> product_price, Context context, Customer_onclick onclick) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.context = context;
        this.onclick = onclick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.customer_product_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String n=product_name.get(position);
        String t=product_price.get(position);
        holder.name.setText(n);
        holder.price.setText(t);
    }

    @Override
    public int getItemCount() {
        return product_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_product);
            price=itemView.findViewById(R.id.price_product);
            itemView.setOnClickListener(view -> {
                onclick.onclick(getAdapterPosition());
            });
        }
    }
}
