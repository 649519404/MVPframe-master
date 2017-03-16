package com.crazypeople.fuc.main.fragment.mine;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crazypeople.R;
import com.crazypeople.SoftApplication;
import com.crazypeople.common.base.baseAdapter.RecycleViewAdapter;
import com.crazypeople.common.base.baseFragment.BaseFragment;
import com.crazypeople.common.base.baseHolder.BaseViewHolder;
import com.crazypeople.common.observer.LoginObserver;
import com.crazypeople.common.observer.Observer;
import com.crazypeople.common.spfs.SharedPrefHelper;
import com.crazypeople.fuc.main.activity.login.LoginActivity;
import com.crazypeople.fuc.main.entity.DataBean;
import com.crazypeople.fuc.main.entity.User;
import com.crazypeople.fuc.main.presenter.MinePresenter;
import com.crazypeople.fuc.main.view.MineView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by 曲志强 on 2017/3/9.
 */

public class MineFragment extends BaseFragment<MinePresenter> implements Observer<User>, MineView<List<DataBean>> {


    @Bind(R.id.review_mine)
    RecyclerView recyclerView;
    @Bind(R.id.la_mine_not_login)
    LinearLayout linearLayout;
    @Bind(R.id.la_mine_login)
    LinearLayout login_la;
    @Bind(R.id.tx_mine_name)
    TextView name;
    @Override
    protected void baseInitView() {

        SoftApplication.loginObserver.register(this);

    }

    @Override
    public void baseInit() {
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        mPresenter.getAll();

    }





    @Override
    protected MinePresenter getChildPresenter() {
        return new MinePresenter<List<DataBean>>(this);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.mine_content_layout;
    }

    @Override
    public void showNetError() {

    }

    @Override
    public void showToastError() {

    }


    @Override
    public void initData(List<DataBean> dataBeen) {
        RecycleViewAdapter  mAdapter = new RecycleViewAdapter<DataBean>(R.layout.mine_list_item, dataBeen) {

            @Override
            protected void convert(BaseViewHolder holder, DataBean dataBeen) {
                holder.setText(R.id.name,dataBeen.getNickName());
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onUpdateUser(LoginObserver<User> observable, User data) {
        name.setText(data.getNickName());
        if(SharedPrefHelper.getInstance().getLoginStatus()){
            linearLayout.setVisibility(View.GONE);
            login_la.setVisibility(View.VISIBLE);
        }else{
            linearLayout.setVisibility(View.VISIBLE);
            login_la.setVisibility(View.GONE);
        }
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            if(parent.getChildPosition(view) != 0)
                outRect.left = space;
        }
    }
}
