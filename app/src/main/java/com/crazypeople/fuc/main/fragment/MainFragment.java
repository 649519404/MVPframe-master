package com.crazypeople.fuc.main.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.crazypeople.R;
import com.crazypeople.common.base.baseFragment.BaseListFragment;
import com.crazypeople.common.base.baseHolder.BaseViewHolder;
import com.crazypeople.common.base.basePresenter.BasePresenter;
import com.crazypeople.fuc.main.entity.DataBean;
import com.crazypeople.fuc.main.presenter.NewsTopPresenter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 曲志强 on 2017/3/15.
 */

public class MainFragment extends BaseListFragment<NewsTopPresenter, DataBean> {


    private MinePagerAdapter minePagerAdapter;
    private LinearLayout group;
    public ViewPager viewPager;
    private int currentItem;
    private ScheduledExecutorService scheduledExecutorService;
    private boolean mIsStop;
    private int oldPostion;

    @Override
    protected Map<String, String> getRequestParams() {
        return null;
    }

    @Override
    protected void fitDates(BaseViewHolder helper, DataBean item) {
        helper.setText(R.id.name, item.getNickName());
    }

    @Override
    protected NewsTopPresenter getChildPresenter() {
        return new NewsTopPresenter(this);
    }

    @Override
    protected void initData(int page, BasePresenter.RequestMode mode) {
        getPresenter().getAll("1", page, mode);
        getPresenter().getPic("1");
    }

    @Override
    protected int getItemLayout() {
        return R.layout.list_item;
    }

    @Override
    protected void addHeader() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.main_fragment_layout, null);
        viewPager = (ViewPager) view.findViewById(R.id.vp_main_header);
        group = (LinearLayout) view.findViewById(R.id.la_main_group);
        minePagerAdapter = new MinePagerAdapter(getActivity());
        viewPager.setAdapter(minePagerAdapter);
        mRecyclerView.addHeaderView(view);
        viewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    mIsStop = true;
                } else if (action == MotionEvent.ACTION_UP) {
                    mIsStop = false;
                }
                return false;
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub

                group.getChildAt(oldPostion).setEnabled(false);
                group.getChildAt(position).setEnabled(true);
                oldPostion=position;
                currentItem = position;

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 每隔2秒钟切换一张图片
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 2, 2, TimeUnit.SECONDS);
        // scheduleAtFixedRate(command, initialDelay, period, unit);
        // command：执行线程 initialDelay：初始化延时 period：前一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间）
        // unit：计时单位
    }
    @Override
    public void onStop() {
        if (scheduledExecutorService!=null){
            scheduledExecutorService.shutdown();
        }
        super.onStop();
    }

    @Override
    public void viewPagerInit(List<DataBean> lists) {
        group.removeAllViews();
        for (int i=0;i<lists.size();i++) {
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.rightMargin = 10;
            ImageView is = new ImageView(getActivity());
            is.setBackgroundResource(R.drawable.selector);
            if(i!=0){
                is.setEnabled(false);
            }
            group.addView(is, layout);
        }

        minePagerAdapter.setHomePics(lists);
        minePagerAdapter.notifyDataSetChanged();
//       if(!scheduledExecutorService.isShutdown()){
//            scheduledExecutorService.shutdown();
//        }


    }

    public class ViewPagerTask implements Runnable {





        @Override
        public void run() {
            currentItem = (currentItem + 1) % minePagerAdapter.getCount();
            handler.obtainMessage().sendToTarget();
        }

        public Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (!mIsStop) {
                    viewPager.setCurrentItem(currentItem);
                }
               // Toast.makeText(getActivity(),currentItem+"",Toast.LENGTH_LONG).show();
            }
        };
    }
}
