package com.home.oleg.mvpplayground.testDi;



import com.home.oleg.mvpplayground.api.ItemsMatchWordsProvider;
import com.home.oleg.mvpplayground.api.di.ApiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestApiModule  extends ApiModule{
    public static TestItemsMatchWordsProvider testItemsMatchWordsProvider = new TestItemsMatchWordsProvider();
    @Singleton
    @Provides
    ItemsMatchWordsProvider getItemsMatchWordsProvider() {
        return testItemsMatchWordsProvider;
    }
}
