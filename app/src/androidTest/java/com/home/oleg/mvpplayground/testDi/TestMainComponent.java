package com.home.oleg.mvpplayground.testDi;


import com.home.oleg.mvpplayground.MainActivity;
import com.home.oleg.mvpplayground.api.ItemsMatchWordsProvider;
import com.home.oleg.mvpplayground.counter.CounterModule;
import com.home.oleg.mvpplayground.di.MainComponent;
import com.home.oleg.mvpplayground.items.match.di.MatchItemsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        TestApiModule.class,
        TestSettingsModule.class,
        CounterModule.class,
        MatchItemsModule.class
})
public interface TestMainComponent extends MainComponent {
    void inject(MainActivity activity);
    ItemsMatchWordsProvider itemsMatchWordsProvider();
}