package com.crazypeople.fuc.main.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crazypeople.MVPframeApplication;
import com.crazypeople.R;
import com.crazypeople.common.spfs.SharedPrefHelper;
import com.crazypeople.fuc.main.activity.BaseActivity;
import com.crazypeople.fuc.main.activity.login.dagger.LoginView;
import com.crazypeople.fuc.main.entity.User;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by 曲志强 on 2017/3/12.
 */

public class LoginActivity extends BaseActivity<LoginPresenter<User>> implements LoginView<User>{


    @Inject
    LoginPresenter loginPresenter;
    @Bind(R.id.btn_login)
    Button btn_login;
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

    }

    @Override
    public void baseInit() {
        changeTitle("登录");
        setSetingVisibility(false);
        btn_login.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                mPresenter.login("15638191708","15638191708");
                break;

        }
    }

    @Override
    public void requestFinish(User user) {
        MVPframeApplication.user=user;
        SharedPrefHelper.getInstance().setLoginStatus(true);
        Toast.makeText(this,"",Toast.LENGTH_LONG).show();

    }
}
