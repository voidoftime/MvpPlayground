package com.home.oleg.mvpplayground;


import com.home.oleg.mvpplayground.counter.CounterModule;
import com.home.oleg.mvpplayground.di.DaggerMainComponent;
import com.home.oleg.mvpplayground.di.MainComponent;
import com.home.oleg.mvpplayground.test.di.DaggerTestMainComponent;
import com.home.oleg.mvpplayground.test.di.TestApiModule;
import com.home.oleg.mvpplayground.test.di.TestSettingsModule;
import com.home.oleg.mvpplayground.items.match.di.MatchItemsModule;

public class TestMainApp extends MainApp {
    @Override
    public MainComponent createComponent() {
        return DaggerTestMainComponent.builder()
//                .settingsModule(new TestSettingsModule())
//                .apiModule(new TestApiModule())
                .counterModule(new CounterModule())
                .matchItemsModule(new MatchItemsModule())
                .build();
    }

}
