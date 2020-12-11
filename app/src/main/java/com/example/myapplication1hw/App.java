package com.example.myapplication1hw;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        My_SPV.init(this);
    }
}
