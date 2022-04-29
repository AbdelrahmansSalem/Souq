package com.example.souq;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class CustomerRegister extends Fragment {

    EditText name;
    EditText mail;
    EditText phone;
    EditText password1;
    EditText password2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_customer_register, container, false);
        //declare sign Variabels

         name=view.findViewById(R.id.csignname);
         mail=view.findViewById(R.id.csignmail);
         phone=view.findViewById(R.id.csignphone);
         password1=view.findViewById(R.id.csignpass1);
         password2=view.findViewById(R.id.csignpass2);

        Button c_signup=view.findViewById(R.id.customersign);
        // Database object
        CustomerDB customerDB=new CustomerDB();
        //customer object

        c_signup.setOnClickListener(view1 -> {
            String n=name.getText().toString();
            String m=mail.getText().toString();
            String p=phone.getText().toString();
            String pass1=password1.getText().toString();
            String pass2=password2.getText().toString();
            if(n.isEmpty() || m.isEmpty() || p.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                Toast.makeText(container.getContext(), "Please Fill All Feilds", Toast.LENGTH_SHORT).show();
            }
            else if(!pass1.equals(pass2)){
                Toast.makeText(container.getContext(), "Passwords Don't Match", Toast.LENGTH_SHORT).show();
            }
            else{
                customerDB.databaseReference.child("All Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(m)){
                            Toast.makeText(container.getContext(), "This Email Is Already Exist", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            customerDB.databaseReference.child("Pending Emails").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.hasChild(m)){
                                        Toast.makeText(container.getContext(), "This Email Is Already Exist", Toast.LENGTH_SHORT).show();
                                    }
                                    else{

                                        Customer customer=new Customer(n,m,p,pass1);
                                        customerDB.add_customer(customer);

                                        customerDB.databaseReference.child("All Users").child(m).child("user_type").setValue("Customer");
                                        Toast.makeText(container.getContext(), "Successful Registration", Toast.LENGTH_SHORT).show();
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