package com.home.oleg.mvpplayground.settings;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsModule {
    @Provides
    ApplicationSettings getApplicationSettings(){
        return new DebugApplicationSettings();
    }
}
