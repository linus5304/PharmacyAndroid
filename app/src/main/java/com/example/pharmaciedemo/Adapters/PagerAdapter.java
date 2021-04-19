package com.example.pharmaciedemo.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pharmaciedemo.Fragments.CategoryFragment;
import com.example.pharmaciedemo.Fragments.DrugsFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
    private int tabsNumber;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fragmentTitle = new ArrayList<>();
    public PagerAdapter(@NonNull FragmentManager fm, int behavior, int tabs) {

        super(fm, behavior);

        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CategoryFragment();
            case 1:
                return new DrugsFragment();
            default:
                return null;
        }



    }

    @Override
    public int getCount() {
        return tabsNumber;
    }

}
