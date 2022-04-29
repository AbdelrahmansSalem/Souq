package com.example.souq;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Seller_my_products extends Fragment {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    ArrayList <String> product_name;
    ArrayList <String> product_price;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view= inflater.inflate(R.layout.fragment_seller_my_products, container, false);
         recyclerView=view.findViewById(R.id.seller_product_list);
        product_name=new ArrayList<>();
        product_price=new ArrayList<>();
         Seller_product_list list=new Seller_product_list(product_name,product_price,getContext());
         recyclerView.setAdapter(list);
         recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),2));

         databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for (DataSnapshot snapshot1: snapshot.child("Products").child("ali").getChildren()){
                     Log.i("child",snapshot1.getValue().toString());

                     product_name.add(snapshot1.child("product_name").getValue().toString());
                     product_price.add(snapshot1.child("product_price").getValue().toString());
                     Log.i("name",product_name.get(0));
                     Log.i("price",product_price.get(0));
                 }
                 list.notifyDataSetChanged();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });


         return view;
    }
}