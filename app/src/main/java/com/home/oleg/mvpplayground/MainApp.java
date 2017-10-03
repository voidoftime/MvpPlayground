package com.home.oleg.mvpplayground;


import android.app.Application;

import com.home.oleg.mvpplayground.api.di.ApiModule;
import com.home.oleg.mvpplayground.counter.CounterModule;
import com.home.oleg.mvpplayground.di.DaggerMainComponent;
import com.home.oleg.mvpplayground.di.MainComponent;
import com.home.oleg.mvpplayground.items.match.di.MatchItemsModule;
import com.home.oleg.mvpplayground.settings.SettingsModule;

public class MainApp extends Application {

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.builder()
                .settingsModule(new SettingsModule())
                .apiModule(new ApiModule())
                .counterModule(new CounterModule())
                .matchItemsModule(new MatchItemsModule())
                .build();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
