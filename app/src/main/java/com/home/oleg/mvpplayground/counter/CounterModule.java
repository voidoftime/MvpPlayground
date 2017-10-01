package com.home.oleg.mvpplayground.counter;

import android.app.Activity;
import android.util.Log;

import com.home.oleg.mvpplayground.MainActivity;
import com.home.oleg.mvpplayground.counter.model.CounterStore;
import com.home.oleg.mvpplayground.counter.model.InMemoryCounterStore;
import com.home.oleg.mvpplayground.counter.presenter.CounterPresenter;
import com.home.oleg.mvpplayground.counter.presenter.CounterPresenterImpl;
import com.home.oleg.mvpplayground.counter.view.BindedCounterView;
import com.home.oleg.mvpplayground.counter.view.CounterView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CounterModule {
    public static final String TAG = "CModuel";
//    private MainActivity activity;

//    public CounterModule(MainActivity activity) {
//        Log.d(TAG, "CounterModule: init");
//        this.activity = activity;
//    }

    @Provides
    @Singleton
    CounterPresenter provideCounterPresenter(CounterPresenterImpl counterPresenter){
        Log.d(TAG,"provideCounterPresenter");
        return counterPresenter;
    }
    @Provides
    @Singleton
    CounterStore provideCounterStore(){
        Log.d(TAG,"provideCounterStore");
        return new InMemoryCounterStore();
    }

//    @Provides
//    @Singleton
//    CounterView provideCounterView(MainActivity activity){
//        Log.d("CModuel","provideCounterView");
//        return new BindedCounterView(activity);
//    }
}
