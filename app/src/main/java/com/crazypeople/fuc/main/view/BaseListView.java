package com.crazypeople.fuc.main.view;


import com.crazypeople.common.base.baseView.BaseView;

import java.util.List;

public interface BaseListView<T> extends BaseView {
    void viewPagerInit(List<T> dates);
    void showFinishDates(List<T> dates);
    void hasNoMoreDate();
    void loadMoreFinish(List dates);
    void refreshView();
    void showRefreshFinish(List score);
}
