package com.crazypeople.fuc.main.fragment.main;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.crazypeople.R;
import com.crazypeople.common.base.baseFragment.BaseFragment;
import com.crazypeople.common.refresh.ProgressStyle;
import com.crazypeople.common.refresh.XRecyclerView;
import com.crazypeople.fuc.main.entity.DataBean;
import com.crazypeople.fuc.main.fragment.MinePagerAdapter;
import com.crazypeople.fuc.main.presenter.MainPresenter;
import com.crazypeople.fuc.main.view.MainView;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 曲志强 on 2017/3/15.
 */

public class MainFragment extends BaseFragment<MainPresenter> implements MainView<DataBean>,XRecyclerView.LoadingListener{


    private MinePagerAdapter minePagerAdapter;
    private LinearLayout group;
    public ViewPager viewPager;
    private int currentItem;
    private ScheduledExecutorService scheduledExecutorService;
    private boolean mIsStop;
    private int oldPostion;

    XRecyclerView mRecyclerView;
    private MainRecyleViewAdapter adapter;

    @Override
    protected Map<String, String> getRequestParams() {
        return null;
    }



    @Override
    protected int getContentLayout() {
        return R.layout.fragment_main_common;
    }

    @Override
    protected void baseInitView() {
        mRecyclerView= (XRecyclerView) mRootView.findViewById(R.id.recycleView);
        addHeader();
        adapter=new MainRecyleViewAdapter(getActivity());
        mRecyclerView.setLoadingMoreEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void baseInit() {
        showLoading();
        mPresenter.getAllRoom();
        mPresenter.getPic("0");
    }

    @Override
    protected View getLoadingTargetView() {
        return mRecyclerView;
    }
    @Override
    protected MainPresenter getChildPresenter() {
        return new MainPresenter(this);
    }




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
    public void showNetError() {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.showNetworkError(v -> {
            showLoading();
           mPresenter.getAllRoom();
            mPresenter.getPic("0");

        });
    }

    @Override
    public void showToastError() {

    }

    @Override
    public void viewPagerInit(List lists) {
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





    @Override
    public void datainit(Map<String, List<DataBean>> lists) {
        refreshView();
        adapter.setMap(lists);
        adapter.notifyDataSetChanged();
        mRecyclerView.refreshComplete();
//        mRecyclerView.refreshComplete();

    }

    @Override
    public void onRefresh() {

        mPresenter.getAllRoom();
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//            if(view instanceof )
          int index=  parent.getChildLayoutPosition(view);
          RecyclerView.LayoutManager layoutManager= parent.getLayoutManager();
            if(view instanceof FrameLayout){

                return;
            }
            if(parent.getChildPosition(view) != 0)
                outRect.top = space;
        }
    }
}
