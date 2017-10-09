package com.home.oleg.mvpplayground.test.di;


import com.home.oleg.mvpplayground.settings.ApplicationSettings;

public class TestApplicationSettings implements ApplicationSettings {
    public String apiUrl="";
    @Override
    public String getApiUrl() {
        return apiUrl;
    }

    @Override
    public String getUserName() {
        return null;
    }
}
