package com.example.souq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Seller_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
        BottomNavigationView bottomNavigationView=findViewById(R.id.seller_bottom);
        NavController navController= Navigation.findNavController(this,R.id.seller_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }
}