package com.crazypeople.fuc.main.fragment.main;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.crazypeople.R;
import com.crazypeople.common.base.baseFragment.BaseFragment;
import com.crazypeople.common.sugar.RoomType;
import com.crazypeople.fuc.main.fragment.FragmentAdapter;

import java.util.List;

import butterknife.Bind;

public class MainContentFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Override
    protected int getContentLayout() {
        return R.layout.main_fragment;
    }

    @Override
    protected void baseInitView() {

    }

    @Override
    public void baseInit() {

       List<RoomType> lists=RoomType.listAll(RoomType.class);
        FragmentAdapter adapter = new FragmentAdapter(getActivity().getSupportFragmentManager(),lists);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public void hasNoMoreDate() {

    }


    public void loadMoreFinish(List dates) {

    }


    public void showRefreshFinish(List score) {

    }

    @Override
    public void showNetError() {

    }

    @Override
    public void showToastError() {

    }


}
