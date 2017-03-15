package com.crazypeople.common.sub;

import com.crazypeople.common.inter.HttpResult;

import rx.Subscriber;

/**
 * Created by 曲志强 on 2017/3/15.
 */

public class SubPro<T> extends Subscriber<HttpResult<T>> {

    private  SubscriberOnNextListener mSubscriberOnNextListener;

    public SubPro(SubscriberOnNextListener mSubscriberOnNextListener){
        this.mSubscriberOnNextListener=mSubscriberOnNextListener;

    }
    @Override
    public void onCompleted() {
        mSubscriberOnNextListener.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mSubscriberOnNextListener.onFail();
    }

    @Override
    public void onNext(HttpResult<T> tHttpResult) {
        mSubscriberOnNextListener.onNext(tHttpResult);
    }
}
