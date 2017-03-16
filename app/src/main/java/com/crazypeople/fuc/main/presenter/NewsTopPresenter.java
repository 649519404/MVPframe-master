package com.crazypeople.fuc.main.presenter;

import com.crazypeople.common.base.basePresenter.BasePresenter;
import com.crazypeople.common.inter.HttpResult;
import com.crazypeople.common.sub.SubPro;
import com.crazypeople.common.sub.SubscriberOnNextListener;
import com.crazypeople.fuc.main.entity.DataBean;
import com.crazypeople.fuc.main.view.BaseListView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class NewsTopPresenter<T> extends BasePresenter {

private BaseListView mMainView;


    @Inject
public NewsTopPresenter(BaseListView mainView){
        mMainView=mainView;
        }

public void getAll(String type,int page,RequestMode mode){
    Observable<HttpResult<List<DataBean>>> observable = connectManager.getAll(type);

        requestDate(observable, mode,new SubPro<T>(new SubscriberOnNextListener() {
            @Override
            public void onNext(HttpResult entity) {
                if (null != entity) {

                    if (null != entity.getData()) {

                        List<T> data = (List<T>) entity.getData();
                        if (null != data && data.size() > 0) {
                            if (mode == RequestMode.FRIST) {
                                mMainView.showFinishDates(data);
                            } else if (mode == RequestMode.LOAD_MORE) {
                                mMainView.loadMoreFinish(data);
                            } else if (mode == RequestMode.REFRESH) {
                                mMainView.showRefreshFinish(data);
                            }
                        } else {
                            if (mode == RequestMode.LOAD_MORE) {
                                mMainView.hasNoMoreDate();
                            } else {
                                mMainView.showEmptyView(null);
                            }
                        }
                    } else {
                        mMainView.showEmptyView(null);
                    }
                } else {
                    mMainView.showEmptyView(null);
                }
            }

            @Override
            public void onFail() {
                if (mode == RequestMode.FRIST) {
                    mMainView.showNetError();
                } else {
                    mMainView.showToastError();
                }
            }

            @Override
            public void onCompleted() {

            }
        }));

    }

    public void getPic(String type){
        Observable<HttpResult<List<DataBean>>> observable = connectManager.getAll(type);
        requestDate(observable,RequestMode.FRIST,new SubPro<T>(new SubscriberOnNextListener() {
            @Override
            public void onNext(HttpResult t) {
                List<T> lists= (List<T>) t.resultData;
                mMainView.viewPagerInit(lists);
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