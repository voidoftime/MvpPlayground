package com.home.oleg.mvpplayground.test.di;



import com.home.oleg.mvpplayground.test.di.TestApplicationSettings;
import com.home.oleg.mvpplayground.settings.ApplicationSettings;
import com.home.oleg.mvpplayground.settings.SettingsModule;

import dagger.Module;
import dagger.Provides;

@Module
public class TestSettingsModule extends SettingsModule {
    @Provides
    ApplicationSettings getApplicationSettings() {
        return new TestApplicationSettings();
    }
}