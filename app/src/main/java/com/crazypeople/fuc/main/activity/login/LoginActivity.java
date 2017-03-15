package com.crazypeople.fuc.main.activity.login;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.crazypeople.MVPframeApplication;
import com.crazypeople.R;
import com.crazypeople.common.spfs.SharedPrefHelper;
import com.crazypeople.fuc.main.activity.BaseActivity;
import com.crazypeople.fuc.main.activity.login.dagger.LoginView;
import com.crazypeople.fuc.main.entity.User;

import javax.inject.Inject;

/**
 * Created by 曲志强 on 2017/3/12.
 */

public class LoginActivity extends BaseActivity<LoginPresenter<User>> implements LoginView<User>{


    @Inject
    LoginPresenter loginPresenter;
    @Override
    protected LoginPresenter getChildPresenter() {
        return new LoginPresenter(this,this);
    }

    @Override
    protected void setContentLayout() {
    setContentView(R.layout.login_layout);
    }

    @Override
    protected void baseInitView() {
        new ProgressDialog(this).show();
    }

    @Override
    public void baseInit() {
        changeTitle("登录");
        setSetingVisibility(false);
        mPresenter.login("15638191708","15638191708");

    }








    @Override
    public void requestFinish(User user) {
        MVPframeApplication.user=user;
        SharedPrefHelper.getInstance().setLoginStatus(true);
        Toast.makeText(this,"",Toast.LENGTH_LONG).show();

    }
}
