package com.pingbits.instaffr_app;


import android.app.Application;

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        DbUtils.initialize(this);
    }
}
