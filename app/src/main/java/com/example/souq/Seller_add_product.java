package com.example.souq;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Seller_add_product extends Fragment {


    DatabaseReference  databaseReference= FirebaseDatabase.getInstance().getReference();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_seller_add_product, container, false);

        EditText product_name=view.findViewById(R.id.product_name);
        EditText product_price=view.findViewById(R.id.product_price);
        Button add_product=view.findViewById(R.id.add_product);
add_product.setOnClickListener(view1 -> {
    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            String email=snapshot.child("current_seller").getValue().toString();
            String p_n=product_name.getText().toString();
            String p_p=product_price.getText().toString();
            String product_num=snapshot.child("Product_ID").getValue().toString();
            Product product=new Product(p_n,p_p);
            databaseReference.child("Products").child(email).child(product_num).setValue(product);
            int n=Integer.parseInt(product_num);
            n++;
            databaseReference.child("Product_ID").setValue(String.valueOf(n));

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
});





        return view;
    }
}