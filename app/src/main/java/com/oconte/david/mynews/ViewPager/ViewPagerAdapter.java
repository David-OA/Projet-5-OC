package com.oconte.david.mynews.ViewPager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.oconte.david.mynews.FragmentMostPopular;
import com.oconte.david.mynews.FragmentSports;
import com.oconte.david.mynews.FragmentTopStories;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * The Fragment for itch positions
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentTopStories();
            case 1:
                return new FragmentMostPopular();
            case 2:
                return new FragmentSports();
        }
        return null;
    }

    /**
     * The title for ich label of this TabLayout.
     */
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

    /**
     * Number of label.
     */
    @Override
    public int getCount() {
        return 3;
    }
}
