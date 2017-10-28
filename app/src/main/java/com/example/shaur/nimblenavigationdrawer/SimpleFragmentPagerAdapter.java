package com.example.shaur.nimblenavigationdrawer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by shaur on 28-10-2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter{
    public SimpleFragmentPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new year1();
        } else if (position == 1){
            return new year2();
        } else if (position == 2) {
            return new year3();
        } else if (position == 3) {
            return new year4();
        }
        else return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
