package com.example.souq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        BottomNavigationView bottomNavigationView=findViewById(R.id.admin_bottom);
        NavController navController= Navigation.findNavController(this,R.id.Admin_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);



    }

}



/*bottomNavigationView.setOnNavigationItemSelectedListener(selectedListener);
    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment=null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            fragment=new Admin_home();
                            break;
                        case R.id.nav_users:
                            fragment=new Admin_users();
                            break;
                        case R.id.nav_pending:
                            fragment=new Admin_pending();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmet,fragment).commit();
                    return true;
                }
            };*/