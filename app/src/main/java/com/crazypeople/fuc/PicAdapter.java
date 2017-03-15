package com.crazypeople.fuc;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by 曲志强 on 2017/1/26.
 */

public class PicAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> lists;

    public PicAdapter(FragmentManager fragmentManager, List<Fragment> lists){
        super(fragmentManager);
        this.lists=lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }
}
