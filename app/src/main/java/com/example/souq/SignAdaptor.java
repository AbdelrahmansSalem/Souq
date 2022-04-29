package com.example.souq;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class SignAdaptor extends FragmentPagerAdapter {

    ArrayList<Fragment> signfragments=new ArrayList<>();
    ArrayList<String> titels=new ArrayList<>();
    public SignAdaptor(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return signfragments.get(position);
    }

    @Override
    public int getCount() {
        return signfragments.size();
    }
    public void addfragmet(Fragment fragment,String s){
        signfragments.add(fragment);
        titels.add(s);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titels.get(position);
    }
}
