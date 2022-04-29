package com.example.souq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;


public class Registration extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        tabLayout =findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.Signup);
        tabLayout.setupWithViewPager(viewPager);
        SignAdaptor signAdaptor=new SignAdaptor(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        signAdaptor.addfragmet(new CustomerRegister(),"Customer Register");
        signAdaptor.addfragmet(new SellerRegister(),"Seller Register");
        viewPager.setAdapter(signAdaptor);

    }
}