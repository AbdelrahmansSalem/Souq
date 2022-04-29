package com.example.souq;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerDB {

    DatabaseReference databaseReference;

    public CustomerDB(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    public void add_customer(Customer customer){
        databaseReference.child("Customer").child(customer.mail).setValue(customer);
        databaseReference.child("All Users").child(customer.mail).setValue(customer);


    }
}
