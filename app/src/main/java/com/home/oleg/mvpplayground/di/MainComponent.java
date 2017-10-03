package com.home.oleg.mvpplayground.di;

import android.app.Activity;

import com.home.oleg.mvpplayground.MainActivity;
import com.home.oleg.mvpplayground.api.di.ApiModule;
import com.home.oleg.mvpplayground.counter.CounterModule;
import com.home.oleg.mvpplayground.items.match.di.MatchItemsModule;
import com.home.oleg.mvpplayground.settings.SettingsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        SettingsModule.class,
        ApiModule.class,
        CounterModule.class,
        MatchItemsModule.class
})
public interface MainComponent {
    void inject(MainActivity activity);
//    CounterModule getCounterModule();
}
