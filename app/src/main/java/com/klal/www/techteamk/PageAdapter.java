package com.klal.www.techteamk;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {
    private int noOfTabs;
    public PageAdapter(FragmentManager fm,int noOfTabs) {
        super(fm);
        this.noOfTabs=noOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AttendanceFragment();
            case 1:
                return new RecordsFragment();
            case 3:
                return new AccountsFragment();
            default:
                return new AccountsFragment();
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
