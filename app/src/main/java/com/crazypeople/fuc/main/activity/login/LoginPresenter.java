package com.crazypeople.fuc.main.activity.login;

import android.content.Context;

import com.crazypeople.common.base.basePresenter.BasePresenter;
import com.crazypeople.common.inter.HttpResult;
import com.crazypeople.common.sub.ProgressSubscriber;
import com.crazypeople.fuc.main.activity.login.dagger.LoginView;

import rx.Observable;

/**
 * Created by 曲志强 on 2017/3/9.
 */

public class LoginPresenter<T> extends BasePresenter {
    private  Context context;
    LoginView loginView;

    public LoginPresenter(Context context,LoginView mineView){
       this.context=context;
        this.loginView=mineView;
    }



    protected void login(String mobile,String password){
        Observable s= connectManager.login(mobile,password);
        ProgressSubscriber<T> progressSubscriber= new ProgressSubscriber<T>(context,this);
        requestDate(s, RequestMode.FRIST,progressSubscriber);
    }


    @Override
    public void onNext(HttpResult t) {
        T data= (T) t.resultData;
        loginView.requestFinish(data);
    }

    @Override
    public void onFail() {

    }

    @Override
    public void onCompleted() {

    }
}
