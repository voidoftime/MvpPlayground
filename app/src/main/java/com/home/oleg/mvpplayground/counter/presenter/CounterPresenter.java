package com.home.oleg.mvpplayground.counter.presenter;


import com.home.oleg.mvpplayground.counter.view.BindedCounterView;
import com.home.oleg.mvpplayground.counter.view.CounterView;

public interface CounterPresenter {
    void onNext();
    void onReset();

    void attach(CounterView view);
}
