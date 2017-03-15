package com.crazypeople.fuc.main.activity.login.dagger;

import com.crazypeople.common.dagger.ActivityScope;
import com.crazypeople.fuc.main.activity.login.LoginActivity;

import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * <b>类名称：</b> LoginComponent <br/>
 * <b>类描述：</b> Component <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月11日 下午5:43<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

@ActivityScope
@Singleton
@Subcomponent( modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
