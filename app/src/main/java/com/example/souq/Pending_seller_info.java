package com.example.souq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pending_seller_info extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_seller_info);
        TextView name=findViewById(R.id.seller_info_name);
        TextView mail=findViewById(R.id.seller_info_email);
        TextView id=findViewById(R.id.seller_info_id);
        TextView phone=findViewById(R.id.seller_info_phone);
        TextView type=findViewById(R.id.seller_info_product_type);



        final String[] current_user = new String[1];
        databaseReference.child("current_user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                current_user[0] =snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        final Seller[] seller = new Seller[1];
        final String[] n =new String[1];
        final String[] m = new String[1];
        final String[] i = new String[1];
        final String[] p = new String[1];
        final String[] pass = new String[1];
        final String[] t = new String[1];
        databaseReference.child("Pending Emails").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                 n[0] =snapshot.child(current_user[0]).child("name").getValue().toString();
                 m[0] =snapshot.child(current_user[0]).child("mail").getValue().toString();
                 i[0] =snapshot.child(current_user[0]).child("id").getValue().toString();
                 p[0] =snapshot.child(current_user[0]).child("phone").getValue().toString();
                 t[0] =snapshot.child(current_user[0]).child("product_type").getValue().toString();
                 pass[0]=snapshot.child(current_user[0]).child("password").getValue().toString();
                name.setText(n[0]);
                mail.setText(m[0]);
                id.setText(i[0]);
                phone.setText(p[0]);
                type.setText(t[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button cancel=findViewById(R.id.cancel);
        cancel.setOnClickListener(view -> {
            startActivity(new Intent(this,Admin_pending.class));
        });

        Button decline=findViewById(R.id.decline);
        decline.setOnClickListener(view -> {
            databaseReference.child("Pending Emails").child(current_user[0]).removeValue();
            Toast.makeText(this, "Just Deleted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,Admin_pending.class));
        });

        Button accept=findViewById(R.id.accept);
        accept.setOnClickListener(view -> {

            seller[0] =new Seller(n[0], m[0], p[0], pass[0], t[0], i[0]);
            databaseReference.child("Seller").child(m[0]).setValue(seller[0]);
            databaseReference.child("All Users").child(m[0]).setValue(seller[0]);
            databaseReference.child("Seller").child(m[0]).child("product_num").setValue("0");
            databaseReference.child("All Users").child(m[0]).child("user_type").setValue("Seller");
            databaseReference.child("Pending Emails").child(current_user[0]).removeValue();
            Toast.makeText(this, "Just Added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,Admin_pending.class));
        });


    }
}