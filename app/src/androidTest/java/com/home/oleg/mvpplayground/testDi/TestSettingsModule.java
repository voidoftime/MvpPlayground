package com.home.oleg.mvpplayground.testDi;



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