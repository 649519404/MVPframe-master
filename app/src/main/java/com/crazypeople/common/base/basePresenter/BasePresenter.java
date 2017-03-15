package com.crazypeople.common.base.basePresenter;

import android.content.Context;

import com.crazypeople.MVPframeApplication;
import com.crazypeople.common.inter.ConnectManager;
import com.crazypeople.common.inter.ConnectService;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class BasePresenter {

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
    public void requestDate(Observable  observable, RequestMode mode, Subscriber schedulers) {
        if (null == observable) {
            throw new IllegalArgumentException("no Observable");
        }

        this.mode = mode;
         observable.subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(schedulers);

    }



    public ConnectManager getService() {
        return connectManager;
    }





}
