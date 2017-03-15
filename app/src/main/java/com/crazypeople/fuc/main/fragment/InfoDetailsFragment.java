package com.crazypeople.fuc.main.fragment;

import android.content.Intent;
import android.view.View;

import com.crazypeople.R;
import com.crazypeople.common.base.baseFragment.BaseListFragment;
import com.crazypeople.common.base.baseHolder.BaseViewHolder;
import com.crazypeople.common.sugar.RoomType;
import com.crazypeople.fuc.detail.VideoViewBuffer;
import com.crazypeople.fuc.main.entity.DataBean;
import com.crazypeople.fuc.main.presenter.NewsTopPresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * <code>Author       : yanbo</code>
 * Date         : 2015-06-01
 * Time         : 15:09
 * Description  :
 */
public class InfoDetailsFragment extends BaseListFragment<NewsTopPresenter,DataBean> {
    private RoomType roomType;

    public static InfoDetailsFragment newInstance(RoomType roomType) {
        InfoDetailsFragment fragment = new InfoDetailsFragment();
        fragment.roomType=roomType;
        return fragment;
    }
    @Override
    protected Map<String, String> getRequestParams() {
      Map<String,String> map=  new HashMap<String,String>();
        map.put("type",roomType.getType());

        return map;
    }

    @Override
    protected void onItemClick(View v, int position) {
        super.onItemClick(v, position);
       mContext.startActivity(new Intent(getActivity(),VideoViewBuffer.class));
    }

    @Override
    public void setupActivityComponent() {

    }

    @Override
    protected NewsTopPresenter getChildPresenter() {
        return new NewsTopPresenter(this);
    }

    @Override
    protected void fitDates(BaseViewHolder helper, DataBean  item) {
            helper.setText(R.id.name,item.getNickName());
    }

    @Override
    protected void initData() {
        getPresenter().getAll();
    }

    @Override
    protected int getItemLayout() {
        return R.layout.list_item;
    }
}
