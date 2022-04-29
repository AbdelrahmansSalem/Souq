package com.example.souq;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SellerDB {
    DatabaseReference databaseReference ;
    public SellerDB (){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    public void pend_mail(Seller seller){
        databaseReference.child("Pending Emails").child(seller.mail).setValue(seller);

    }
}
