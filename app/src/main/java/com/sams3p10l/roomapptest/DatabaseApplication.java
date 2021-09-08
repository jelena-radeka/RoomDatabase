package com.sams3p10l.roomapptest;

import android.app.Application;

public class DatabaseApplication extends Application {

    private static DatabaseApplication instance;

    public static synchronized DatabaseApplication getInstance() {
        if (instance == null) {
            instance = new DatabaseApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
