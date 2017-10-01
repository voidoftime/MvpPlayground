package com.home.oleg.mvpplayground.items.match.presenter;


import com.home.oleg.mvpplayground.base.BasePresenter;
import com.home.oleg.mvpplayground.items.match.view.ForeignItemsView;

public interface ForeignItemsPresenter extends BasePresenter<ForeignItemsView>{
    void onClickItem(String value);
}
