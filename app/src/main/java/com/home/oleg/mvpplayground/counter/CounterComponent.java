package com.home.oleg.mvpplayground.counter;

import android.app.Activity;

import com.home.oleg.mvpplayground.MainActivity;
import com.home.oleg.mvpplayground.items.match.di.MatchItemsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CounterModule.class, MatchItemsModule.class})
public interface CounterComponent {
    void inject(MainActivity activity);
//    CounterModule getCounterModule();
}
