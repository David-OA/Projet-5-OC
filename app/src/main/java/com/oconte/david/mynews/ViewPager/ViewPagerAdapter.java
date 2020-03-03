package com.oconte.david.mynews.ViewPager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                return new MainFragment();
            case 1:

                return new MainFragment2();
            case 2:

                return new MainFragment3();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "TOP STORIES";
            case 1:
                return "MOST POPULAR";
            case 2:
                return "SPORTS";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return (3);
    }
}
