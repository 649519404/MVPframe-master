package com.crazypeople.fuc.main.presenter;

import com.crazypeople.common.base.basePresenter.BasePresenter;
import com.crazypeople.common.inter.HttpResult;
import com.crazypeople.common.sub.SubPro;
import com.crazypeople.common.sub.SubscriberOnNextListener;
import com.crazypeople.fuc.main.view.MineView;

import javax.inject.Inject;

/**
 * Created by 曲志强 on 2017/3/9.
 */

public class MinePresenter<T> extends BasePresenter  {
    MineView mineView;

    @Inject
    public MinePresenter (MineView mineView){
        super();
        this.mineView=mineView;
    }

    public void getAll(){
        SubPro<T> subscriber=new SubPro<T>(new SubscriberOnNextListener() {
            @Override
            public void onNext(HttpResult t) {
                T list= (T) t.resultData;
                mineView.initData(list);
            }

            @Override
            public void onFail() {
                mineView.getClass();
            }

            @Override
            public void onCompleted() {

            }
        });
        requestDate(connectManager.getAll("1"), RequestMode.FRIST,subscriber);

    }



}
