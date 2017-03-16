package com.crazypeople.fuc.main.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.crazypeople.common.sugar.RoomType;

import java.util.List;

/**
 * Author       : yanbo
 * Date         : 2015-06-01
 * Time         : 15:32
 * Description  :
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<RoomType> roomTypes;

    public FragmentAdapter(FragmentManager fm, List<RoomType> fragments) {
        super(fm);
        roomTypes = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return new HallFragment(roomTypes.get(position));
    }

    @Override
    public int getCount() {
        return roomTypes.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return roomTypes.get(position).getName();
    }
}
