package com.crazypeople.common.base.baseView;

import com.crazypeople.common.sub.SubPro;

import rx.Observable;

public interface BaseView {

    void showLoading();



    void showNetError(Observable observable, SubPro subscriberOnNextListener);

    void showEmptyView(String msg);



    void showToastError();
}
