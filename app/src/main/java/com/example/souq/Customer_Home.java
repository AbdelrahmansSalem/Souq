package com.example.souq;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.souq.databinding.FragmentCustomerHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Customer_Home extends Fragment {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    RecyclerView recyclerView;
    ArrayList<String> names ;
    ArrayList<String> prices;
    DatabaseReference reference =FirebaseDatabase.getInstance().getReference();
  FragmentCustomerHomeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentCustomerHomeBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        names =new ArrayList<>();
        prices=new ArrayList<>();
        recyclerView=binding.customerProductsList;
        Customer_product_list list=new Customer_product_list(names,prices,getContext(),position -> {
            binding.selectProduct.animate().alpha(1f).setDuration(1000);
            binding.productName.setText(names.get(position));
            binding.productPrice.setText(prices.get(position));
            binding.plus.setOnClickListener(view1 -> {
                int amount =Integer.parseInt(binding.porductAmount.getText().toString());
                amount++;
                binding.porductAmount.setText(String.valueOf(amount));
                binding.productPrice.setText(String.valueOf(amount*Integer.valueOf(prices.get(position))));
            });
            binding.minus.setOnClickListener(view1 -> {
                int amount =Integer.parseInt(binding.porductAmount.getText().toString());
                if(amount==1){

                }
                else {
                    amount--;
                }
                binding.porductAmount.setText(String.valueOf(amount));
                binding.productPrice.setText(String.valueOf(amount*Integer.valueOf(prices.get(position))));
            });
            binding.cancel.setOnClickListener(view1 -> {
                binding.selectProduct.animate().alpha(0f).setDuration(1000);
            });
            binding.add.setOnClickListener(view1 -> {
                binding.selectProduct.animate().alpha(0f).setDuration(1000);
            });
        });
        recyclerView.setAdapter(list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        databaseReference.child("Products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    for (DataSnapshot snapshot2 :snapshot1.getChildren()){
                        names.add(snapshot2.child("product_name").getValue().toString());
                        prices.add(snapshot2.child("product_price").getValue().toString());
                    }
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