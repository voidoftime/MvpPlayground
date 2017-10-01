package com.home.oleg.mvpplayground;


import android.app.Application;

import com.home.oleg.mvpplayground.counter.CounterComponent;
import com.home.oleg.mvpplayground.counter.CounterModule;
import com.home.oleg.mvpplayground.counter.DaggerCounterComponent;
import com.home.oleg.mvpplayground.items.match.di.MatchItemsModule;

public class MainApp extends Application {

    private CounterComponent counterComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        counterComponent = DaggerCounterComponent.builder()
                .counterModule(new CounterModule())
                .matchItemsModule(new MatchItemsModule())
                .build();
    }

    public CounterComponent getCounterComponent() {
        return counterComponent;
    }
}
