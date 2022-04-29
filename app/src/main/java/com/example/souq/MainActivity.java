package com.example.souq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    private final String admin_username="Abdelrahman.Admin";
    private final String admin_password="123456789";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.signup);
        button.setOnClickListener(view -> {
            startActivity(new Intent(this,Registration.class));
        });

        EditText logmail=findViewById(R.id.loginmail);
        EditText logpass=findViewById(R.id.loginpass);

        Button login=findViewById(R.id.login);

        login.setOnClickListener(view -> {
            String email=logmail.getText().toString();
            String pass=logpass.getText().toString();

            if(email.equals(admin_username) && pass.equals(admin_password)){
                startActivity(new Intent(this,Admin.class));

            }
            else if(email.isEmpty() || pass.isEmpty()){
                Toast.makeText(this, "Please Fill All Feilds", Toast.LENGTH_SHORT).show();

            }
            else if(!email.isEmpty() && !pass.isEmpty()) {
                databaseReference.child("Pending Emails").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(email)) {
                            Toast.makeText(MainActivity.this, "This Email Still in Pending list,Check later", Toast.LENGTH_LONG).show();
                        }
                        else{
                            databaseReference.child("Customer").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    final String getpass = snapshot.child(email).child("password").getValue(String.class);
                                    if (snapshot.hasChild(email)) {
                                        if (getpass.equals(pass)) {
                                            databaseReference.child("current_customer").setValue(email);
                                            startActivity(new Intent(MainActivity.this, CustomerActivity.class));
                                        } else {
                                            Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {
                                        databaseReference.child("Seller").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                final String getpass = snapshot.child(email).child("password").getValue(String.class);
                                                if(snapshot.hasChild(email)){
                                                    if(getpass.equals(pass)){
                                                        databaseReference.child("current_seller").setValue(email);
                                                        startActivity(new Intent(MainActivity.this,Seller_activity.class));
                                                    }
                                                    else{
                                                        Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                                else {
                                                    Toast.makeText(MainActivity.this, "INVALID MAIL", Toast.LENGTH_SHORT).show();
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
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

        });

    }
}