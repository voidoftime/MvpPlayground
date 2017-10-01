package com.home.oleg.mvpplayground.counter.presenter;


import android.util.Log;

import com.home.oleg.mvpplayground.counter.model.CounterStore;
import com.home.oleg.mvpplayground.counter.view.CounterView;

import javax.inject.Inject;

public class CounterPresenterImpl implements CounterPresenter {
    public static final String TAG = "CPI";

    private final CounterStore counterStore;

    @Inject
    public CounterPresenterImpl(CounterStore counterStore) {
        Log.d(TAG,"provideCounterPresenter impl");
        this.counterStore = counterStore;
    }
    private CounterView view;

    @Override
    public void onNext() {
        Log.d(TAG, "onNext: ");
        counterStore.increase();
        showCounter();
    }

    @Override
    public void onReset() {
        Log.d(TAG, "onReset: ");
        counterStore.clear();
        showCounter();
    }

    private void showCounter() {
        view.setCurrentCount(counterStore.get());
    }

    @Override
    public void attach(CounterView view) {
        this.view = view;
        showCounter();
    }
}
