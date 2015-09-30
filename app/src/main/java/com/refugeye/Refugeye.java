package com.refugeye;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Refugeye extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
