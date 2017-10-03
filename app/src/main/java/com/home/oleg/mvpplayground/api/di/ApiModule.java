package com.home.oleg.mvpplayground.api.di;

import com.home.oleg.mvpplayground.api.ItemsMatchWordsProvider;
import com.home.oleg.mvpplayground.api.RemoteItemsMatchWordsProvider;
import com.home.oleg.mvpplayground.settings.ApplicationSettings;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {
    @Provides
    ItemsMatchWordsProvider getItemsMatchWordsProvider(RemoteItemsMatchWordsProvider itemsMatchWordsProvider){
        return itemsMatchWordsProvider;
    }
}
