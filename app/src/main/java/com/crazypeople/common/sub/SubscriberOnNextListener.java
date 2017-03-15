package com.crazypeople.common.sub;

import com.crazypeople.common.inter.HttpResult;

public interface SubscriberOnNextListener<T> {
    void onNext(HttpResult<T> t);
    void onFail();
    void onCompleted();
}