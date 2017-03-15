package com.crazypeople.fuc.main.activity.login.dagger;


import dagger.Module;
import dagger.Provides;

/**
 * <b>类名称：</b> LoginModule <br/>
 * <b>类描述：</b> Module 类<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月11日 下午5:45<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class LoginModule {

    private LoginView view;

    public LoginModule(LoginView  view){
        this.view = view;
    }



     @Provides
    public LoginView providerView(){
        return view;
    }

}
