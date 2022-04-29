package com.example.souq;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class SellerRegister extends Fragment {
    String[] product_items={"Electronics","Toys","Clothes","Accessories","Other"};
    AutoCompleteTextView autoCompleteText;

    String product_type;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view= inflater.inflate(R.layout.fragment_seller_resgister, container, false);


        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(container.getContext(),R.layout.product_type,product_items);
        autoCompleteText= view.findViewById(R.id.producttype);
        autoCompleteText.setAdapter(adapter);

        EditText name=view.findViewById(R.id.ssignname);
        EditText mail=view.findViewById(R.id.ssignmail);
        EditText phone=view.findViewById(R.id.ssignphone);
        EditText id=view.findViewById(R.id.sid);
        EditText password1=view.findViewById(R.id.ssignpass1);
        EditText password2=view.findViewById(R.id.ssignpass2);

        Button s_signup=view.findViewById(R.id.sellersign);
        // Database object
        SellerDB sellerDB=new SellerDB();
        //customer object

        autoCompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                product_type=adapterView.getItemAtPosition(position).toString();
            }
        });
        s_signup.setOnClickListener(view1 -> {
            String n=name.getText().toString();
            String m=mail.getText().toString();
            String p=phone.getText().toString();
            String ID=id.getText().toString();
            String pass1=password1.getText().toString();
            String pass2=password2.getText().toString();
            if(n.isEmpty() || m.isEmpty() || p.isEmpty() || pass1.isEmpty() || pass2.isEmpty() || ID.isEmpty()){
                Toast.makeText(container.getContext(), "Please Fill All Feilds", Toast.LENGTH_SHORT).show();
            }
            else if(!pass1.equals(pass2)){
                Toast.makeText(container.getContext(), "Passwords Don't Match", Toast.LENGTH_SHORT).show();
            }
            else{
                sellerDB.databaseReference.child("All Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(m)){
                            Toast.makeText(container.getContext(), "This Email Is Already Exist", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            sellerDB.databaseReference.child("Pending Emails").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.hasChild(m)){
                                        Toast.makeText(container.getContext(), "This Email Is Already Exist", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Seller seller=new Seller(n,m,p,pass1,product_type,ID);
                                        sellerDB.pend_mail(seller);
                                        Toast.makeText(container.getContext(), "Wait Admin Acception", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(container.getContext(),MainActivity.class));
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });

        return view;
    }
}