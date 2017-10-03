package com.home.oleg.mvpplayground.items.match.di;

import com.home.oleg.mvpplayground.items.match.model.InMemoryMatchItemsStore;
import com.home.oleg.mvpplayground.items.match.model.MatchItemsStore;
import com.home.oleg.mvpplayground.items.match.presenter.ForeignItemsPresenter;
import com.home.oleg.mvpplayground.items.match.presenter.ForeignItemsPresenterImpl;
import com.home.oleg.mvpplayground.items.match.presenter.NativeItemsPresenter;
import com.home.oleg.mvpplayground.items.match.presenter.NativeItemsPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MatchItemsModule {
    @Provides
    @Singleton
    MatchItemsStore provideMatchItemsStore(InMemoryMatchItemsStore matchItemsStore){
        return matchItemsStore;
    }

    @Provides
    ForeignItemsPresenter provideForeignItemsPresenter(ForeignItemsPresenterImpl foreignItemsPresenter){
        return foreignItemsPresenter;
    }
    @Provides
    NativeItemsPresenter provideNativeItemsPresenter(NativeItemsPresenterImpl presenter){
        return presenter;
    }
}
