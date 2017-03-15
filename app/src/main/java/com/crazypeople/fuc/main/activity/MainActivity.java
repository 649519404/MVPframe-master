package com.crazypeople.fuc.main.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.crazypeople.R;
import com.crazypeople.common.base.basePresenter.BasePresenter;
import com.crazypeople.fuc.PicAdapter;
import com.crazypeople.fuc.main.fragment.MainFragment;
import com.crazypeople.fuc.main.fragment.mine.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {


    @Bind(R.id.main_view_pager)
    ViewPager mViewPager;
    @Bind(R.id.la1)
    View la1;
    @Bind(R.id.la2)
    View la2;
    @Bind(R.id.la3)
    View la3;
    @Bind(R.id.la4)
    View la4;


    @Override
    protected BasePresenter getChildPresenter() {
        return null;
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_main);
    }



    @Override
    protected void baseInitView() {
        setSetingVisibility(false);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        changeTitle("首页");
                        setSetingVisibility(false);
                        setBackVisibility(false);
                        break;
                    case 1:
                        changeTitle("直播大厅");
                        setSetingVisibility(false);
                        setBackVisibility(false);
                        break;
                    case 2:
                        changeTitle("直播分类");
                        setSetingVisibility(false);
                        setBackVisibility(false);
                        break;
                    case 3:
                        changeTitle("我的");
                        setSetingVisibility(true);
                        setBackVisibility(false);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void baseInit() {
        //初始化ViewPager的数据集
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment());
//        fragments.add(new JokeFragment());fragments.add(new JokeFragment());
        fragments.add(new MineFragment());
//        fragments.add(new AgendaFragment());
        //创建ViewPager的adapter
        PicAdapter adapter = new PicAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(0);
    }



    public void toDetail(String url) {
    }

    public void onBack(){
    }

    @Override
    public void onBackPressed() {
    }





}
