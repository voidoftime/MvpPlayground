package com.home.oleg.mvpplayground.di;


import com.home.oleg.mvpplayground.MainActivity;
import com.home.oleg.mvpplayground.test.di.TestApiModule;
import com.home.oleg.mvpplayground.test.di.TestSettingsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        TestApiModule.class,
        TestSettingsModule.class
})
public interface TestMainComponent{//} extends MainComponent {
    void inject(MainActivity activity);
}
