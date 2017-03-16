package com.crazypeople.fuc.main.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.crazypeople.R;
import com.crazypeople.common.base.basePresenter.BasePresenter;
import com.crazypeople.common.spfs.SharedPrefHelper;
import com.crazypeople.fuc.PicAdapter;
import com.crazypeople.fuc.main.fragment.MainContentFragment;
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
    @Bind(R.id.img_la1)
    ImageView img_la1;
    @Bind(R.id.img_la2)
    ImageView img_la2;
    @Bind(R.id.img_la3)
    ImageView img_la3;
    @Bind(R.id.img_la4)
    ImageView img_la4;
    @Bind(R.id.tx_la1)
    TextView tx_la1;
    @Bind(R.id.tx_la2)
    TextView tx_la2;
    @Bind(R.id.tx_la3)
    TextView tx_la3;
    @Bind(R.id.tx_la4)
    TextView tx_la4;

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
        la1.setOnClickListener(this);
        la2.setOnClickListener(this);
        la3.setOnClickListener(this);
        la4.setOnClickListener(this);

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
                        setBottmBacground(0);
                        break;
                    case 1:
                        changeTitle("直播大厅");
                        setSetingVisibility(false);
                        setBackVisibility(false);
                        setBottmBacground(1);
                        break;
                    case 2:
                        changeTitle("直播分类");
                        setSetingVisibility(false);
                        setBackVisibility(false);
                        setBottmBacground(3);
                        break;
                    case 3:
                        changeTitle("我的");
                        setSetingVisibility(true);
                        setBackVisibility(false);
                        setBottmBacground(2);
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
        fragments.add(new MainContentFragment());
//        fragments.add(new JokeFragment());fragments.add(new JokeFragment());

        fragments.add(new MineFragment());

        //创建ViewPager的adapter
        PicAdapter adapter = new PicAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(0);
    }

    public void setBottmBacground(int postion){
        switch (postion)
        {
            case 0:
                img_la1.setImageResource(R.mipmap.sy);
                img_la2.setImageResource(R.mipmap.zb_none);
                img_la3.setImageResource(R.mipmap.yw);
                img_la4.setImageResource(R.mipmap.mine_none);

                tx_la1.setTextColor(getResources().getColor(R.color.colorPrimary));
                tx_la2.setTextColor(getResources().getColor(R.color.gary_text));
                tx_la3.setTextColor(getResources().getColor(R.color.gary_text));
                tx_la4.setTextColor(getResources().getColor(R.color.gary_text));

                break;
            case 1:
                img_la1.setImageResource(R.mipmap.sy_none);
                img_la2.setImageResource(R.mipmap.zb);
                img_la3.setImageResource(R.mipmap.yw);
                img_la4.setImageResource(R.mipmap.mine_none);

                tx_la1.setTextColor(getResources().getColor(R.color.gary_text));
                tx_la2.setTextColor(getResources().getColor(R.color.colorPrimary));
                tx_la3.setTextColor(getResources().getColor(R.color.gary_text));
                tx_la4.setTextColor(getResources().getColor(R.color.gary_text));
                break;
            case 2:
                img_la1.setImageResource(R.mipmap.sy_none);
                img_la2.setImageResource(R.mipmap.zb_none);
                img_la3.setImageResource(R.mipmap.yw);
                img_la4.setImageResource(R.mipmap.mine_none);


                tx_la1.setTextColor(getResources().getColor(R.color.gary_text));
                tx_la2.setTextColor(getResources().getColor(R.color.gary_text));
                tx_la3.setTextColor(getResources().getColor(R.color.colorPrimary));
                tx_la4.setTextColor(getResources().getColor(R.color.gary_text));
                break;
            case 3:
                img_la1.setImageResource(R.mipmap.sy_none);
                img_la2.setImageResource(R.mipmap.zb_none);
                img_la3.setImageResource(R.mipmap.yw);
                img_la4.setImageResource(R.mipmap.mine);

                tx_la1.setTextColor(getResources().getColor(R.color.gary_text));
                tx_la2.setTextColor(getResources().getColor(R.color.gary_text));
                tx_la3.setTextColor(getResources().getColor(R.color.gary_text));
                tx_la4.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;

        }
    }

    @Override
    public void onClickEvent(View view) {
        super.onClickEvent(view);
        switch (view.getId()){
            case R.id.la1:
                setBottmBacground(0);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.la2:
                setBottmBacground(1);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.la3:
                setBottmBacground(2);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.la4:
                setBottmBacground(3);
                mViewPager.setCurrentItem(3);
                break;

        }
    }

    public void toDetail(String url) {
    }

    public void onBack(){
    }

    @Override
    public void onBackPressed() {
        SharedPrefHelper.getInstance().setLoginStatus(false);
        finish();
    }


}
