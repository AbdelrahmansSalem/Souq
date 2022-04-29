package com.example.souq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.souq.databinding.ActivityCustomerBinding;

public class CustomerActivity extends AppCompatActivity {

    ActivityCustomerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCustomerBinding.inflate(getLayoutInflater());
        View view =binding.getRoot();
        setContentView(view);
        NavigationUI.setupWithNavController(binding.cutomerbottom, Navigation.findNavController(this,R.id.customer_fragment));
    }
}