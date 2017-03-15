package com.crazypeople.common.sub;

import android.content.Context;

import com.crazypeople.common.inter.HttpResult;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 * Created by liukun on 16/3/10.
 */
public class ProgressSubscriber<T> extends Subscriber<HttpResult<T>> implements ProgressCancelListener{

    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler progressDialogHandler;

    private Context context;

    public ProgressSubscriber(Context context,SubscriberOnNextListener mSubscriberOnNextListener) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        progressDialogHandler=new ProgressDialogHandler(context,this,true);

    }



    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        progressDialogHandler.sendEmptyMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG);
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {

       // Toast.makeText(context, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     * @param e
     */
    @Override
    public void onError(Throwable e) {

        if (e instanceof SocketTimeoutException) {
          mSubscriberOnNextListener.onFail();
        } else if (e instanceof ConnectException) {
            mSubscriberOnNextListener.onFail();
        } else {
            mSubscriberOnNextListener.onFail();
        }
        progressDialogHandler.sendEmptyMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG);

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(HttpResult<T> t) {
        progressDialogHandler.sendEmptyMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG);
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }



    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
        progressDialogHandler.sendEmptyMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG);
    }
}