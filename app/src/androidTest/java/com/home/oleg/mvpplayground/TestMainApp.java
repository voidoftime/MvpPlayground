package com.home.oleg.mvpplayground;


import com.home.oleg.mvpplayground.counter.CounterModule;
import com.home.oleg.mvpplayground.di.MainComponent;
import com.home.oleg.mvpplayground.items.match.di.MatchItemsModule;
import com.home.oleg.mvpplayground.testDi.DaggerTestMainComponent;
import com.home.oleg.mvpplayground.testDi.TestApiModule;
import com.home.oleg.mvpplayground.testDi.TestSettingsModule;

import javax.inject.Singleton;

import dagger.Component;

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
