package com.home.oleg.mvpplayground.settings;


public class DebugApplicationSettings implements ApplicationSettings {
    @Override
    public String getApiUrl() {
        return "http://www.mocky.io/v2/59d3d4a52700004c0107b1f5/";
    }

    @Override
    public String getUserName() {
        return "oleg";
    }
}
