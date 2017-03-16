package com.crazypeople.common.base.baseFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crazypeople.R;
import com.crazypeople.common.base.basePresenter.BasePresenter;
import com.crazypeople.common.base.baseView.BaseView;
import com.crazypeople.common.loading.VaryViewHelperController;

import java.lang.reflect.Field;
import java.util.Map;

import butterknife.ButterKnife;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements
        BaseView {

    protected Activity mContext;

    protected VaryViewHelperController mVaryViewHelperController;

    protected Toolbar mToolbar;

    private TextView mTitleView;

    protected View mRootView;

    protected T mPresenter;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (Activity) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(getContentLayout(), container, false);

            ButterKnife.bind(this, mRootView);
            baseInitView();

            mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
            if (null != getLoadingTargetView()) {
                mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
            }

            if (null != getChildPresenter()) {
                mPresenter = getChildPresenter();
            }

            baseInit();
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }
    protected abstract int getContentLayout();
    protected abstract void baseInitView() ;
    public abstract void baseInit();
    protected T getChildPresenter() {
        return null;
    }








    public T getPresenter(){
        return mPresenter;

    }

    protected View getLoadingTargetView() {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showLoading() {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.showLoading();
    }


    public void refreshView() {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.restore();
    }



    protected Map<String, String> getRequestParams() {
        return null;
    }

    public void showEmptyView(String msg) {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.showEmpty(msg);
    }

}
