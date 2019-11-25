package com.example.chanyoung.se;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Home_Fragment home_fragment = new Home_Fragment();
                return home_fragment;
            case 1:
                ProductList_Fragment itemList_fragment = new ProductList_Fragment();
                return itemList_fragment;
            case 2:
                Border_Fragment border_fragment = new Border_Fragment();
                return border_fragment;
            case 3:
                Friends_Fragment friends_fragment = new Friends_Fragment();
                return friends_fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
