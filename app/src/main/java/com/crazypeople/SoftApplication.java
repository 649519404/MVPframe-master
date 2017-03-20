package com.crazypeople;

import android.app.Application;
import android.content.Context;

import com.crazypeople.common.config.AppConfig;
import com.crazypeople.common.dagger.MVPframeComponent;
import com.crazypeople.common.observer.LoginObserver;
import com.crazypeople.common.spfs.SharedPrefHelper;
import com.crazypeople.fuc.main.entity.User;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orm.SugarContext;


public class SoftApplication extends Application {

    private MVPframeComponent mComponent;

    public static Context mContext;
    public static User user;
    public static LoginObserver<User> loginObserver;
    public static ImageLoader loader;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mComponent = MVPframeComponent.MVPframeInitialize.init();
        loginObserver=new LoginObserver<User>();
        SugarContext.init(this);
        if(!SharedPrefHelper.getInstance().getIsFirst()){
            AppConfig.getAppConfigInfo(this);
            SharedPrefHelper.getInstance().setIsFirst(true);
        }
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        loader=ImageLoader.getInstance();

        loader.init(config);


    }

    public static MVPframeComponent getComponent() {
        return ((SoftApplication) mContext.getApplicationContext()).mComponent;
    }



    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
