package com.crazypeople.fuc.main.presenter;

import com.crazypeople.common.base.basePresenter.BasePresenter;
import com.crazypeople.common.inter.HttpResult;
import com.crazypeople.common.sub.SubPro;
import com.crazypeople.common.sub.SubscriberOnNextListener;
import com.crazypeople.fuc.main.entity.DataBean;
import com.crazypeople.fuc.main.view.MainView;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 曲志强 on 2017/3/17.
 */

public class MainPresenter<T> extends BasePresenter {

    private MainView mineview;

    public MainPresenter(MainView mineview) {
        this.mineview = mineview;
    }

    public void getAllRoom(){

        requestDate(connectManager.getAllRoom(), RequestMode.FRIST, new Subscriber<HttpResult<Map<String,List<T>>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mode == RequestMode.FRIST) {
                    mineview.showNetError();
                } else {
                    mineview.showToastError();
                }
            }

            @Override
            public void onNext(HttpResult<Map<String,List<T>>> o) {

            mineview.datainit( o.resultData);
            }
        });
    }

    public void getPic(String type) {
        Observable<HttpResult<List<DataBean>>> observable = connectManager.getAll(type);
        requestDate(observable, RequestMode.FRIST, new SubPro<T>(new SubscriberOnNextListener() {
            @Override
            public void onNext(HttpResult t) {
                List<T> lists = (List<T>) t.resultData;
                mineview.viewPagerInit(lists);
            }

            @Override
            public void onFail() {

            }

            @Override
            public void onCompleted() {

            }
        }));
    }
}
