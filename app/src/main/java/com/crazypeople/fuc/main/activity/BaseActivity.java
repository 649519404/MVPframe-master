package com.crazypeople.fuc.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.crazypeople.R;
import com.crazypeople.common.base.basePresenter.BasePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 曲志强 on 2017/3/12.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements View.OnClickListener {

    protected Activity mContext;


    protected Toolbar mToolbar;
    @Bind(R.id.toolbar_title)
    public TextView mTitleView;
    @Bind(R.id.toolbar_set)
    public TextView seting;
    @Bind(R.id.toolbar_back)
    public ImageView mImageView;
    protected View mRootView;
    protected T mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout();
      //  SoftApplication.getComponent().inject(this);
        ButterKnife.bind(this);

        baseInitView();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        if (null != getChildPresenter()) {
            mPresenter = getChildPresenter();
        }
        baseInit();
        mImageView.setOnClickListener(this);

    }

    protected abstract T getChildPresenter();

    protected abstract void setContentLayout();


    protected abstract void baseInitView() ;

    public abstract void baseInit();
    /**
     * onClick方法的封装，在此方法中处理点击
     *
     * @param view
     *            被点击的View对象
     */
    public void onClickEvent(View view){
        switch (view.getId()){
            case R.id.toolbar_back:
                finish();
                break;

        }

    }

    @Override
    public void onClick(View v) {
        onClickEvent(v);
    }

    protected void changeTitle(String title) {
        if (null != mTitleView) {
            mTitleView.setText(title);
        }
    }

    protected void setSetingText(String title) {
        if (null != seting) {
            seting.setText(title);
        }
    }

    protected void setSetingVisibility(boolean isVisibility) {
        if (null != seting) {
            if(isVisibility) {
                seting.setVisibility(View.VISIBLE);
            }else{
                seting.setVisibility(View.GONE);
            }
        }
    }

    protected void setBackVisibility(boolean isVisibility) {
        if (null != seting) {
            if(isVisibility) {
                mImageView.setVisibility(View.VISIBLE);
            }else{
                mImageView.setVisibility(View.GONE);
            }
        }
    }


    protected void onBackClick() {

    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
