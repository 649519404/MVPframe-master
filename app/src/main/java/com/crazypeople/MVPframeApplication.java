package com.crazypeople;

import android.app.Application;
import android.content.Context;

import com.crazypeople.common.config.AppConfig;
import com.crazypeople.common.dagger.MVPframeComponent;
import com.crazypeople.common.spfs.SharedPrefHelper;
import com.crazypeople.fuc.main.entity.User;
import com.orm.SugarContext;

import javax.inject.Inject;


public class MVPframeApplication extends Application {

    private MVPframeComponent mComponent;

    public static Context mContext;
    public static User user;
    @Inject
    SharedPrefHelper sharedPrefHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mComponent = MVPframeComponent.MVPframeInitialize.init();

        SugarContext.init(this);
        if(!SharedPrefHelper.getInstance().getIsFirst()){
            AppConfig.getAppConfigInfo(this);
            SharedPrefHelper.getInstance().setIsFirst(true);
        }

    }

    public static MVPframeComponent getComponent() {
        return ((MVPframeApplication) mContext.getApplicationContext()).mComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
