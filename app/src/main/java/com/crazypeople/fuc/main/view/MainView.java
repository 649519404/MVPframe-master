package com.crazypeople.fuc.main.view;

import com.crazypeople.common.base.baseView.BaseView;

import java.util.List;
import java.util.Map;

/**
 * Created by 曲志强 on 2017/3/15.
 */

public interface MainView<T> extends BaseView {
    void viewPagerInit(List<T> lists);
    void datainit(Map<String,List<T>> lists);
}
