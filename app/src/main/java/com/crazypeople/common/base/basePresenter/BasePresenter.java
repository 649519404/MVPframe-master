package com.crazypeople.common.base.basePresenter;

import android.content.Context;

import com.crazypeople.MVPframeApplication;
import com.crazypeople.common.inter.ConnectManager;
import com.crazypeople.common.inter.ConnectService;
import com.crazypeople.common.sub.SubPro;
import com.crazypeople.common.sub.SubscriberOnNextListener;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class BasePresenter implements SubscriberOnNextListener {

    private  Context context;
    @Inject
    ConnectService mService;



    @Inject
    public ConnectManager connectManager;

    protected RequestMode mode = RequestMode.FRIST;

    public BasePresenter() {

        MVPframeApplication.getComponent().inject(this);
        System.out.print("");
    }

    public enum RequestMode {
        FRIST, LOAD_MORE, REFRESH
    }

    @SuppressWarnings("unchecked")
    public void requestDate(Observable  observable, RequestMode mode, Subscriber subscriber) {
        if (null == observable) {
            throw new IllegalArgumentException("no Observable");
        }

        this.mode = mode;
        try {

            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @SuppressWarnings("unchecked")
    public void requestDate(Observable  observable, RequestMode mode) {
        if (null == observable) {
            throw new IllegalArgumentException("no Observable");
        }

        this.mode = mode;
        try {

            SubPro subscriber = new SubPro(this);

            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public ConnectManager getService() {
        return connectManager;
    }





}
