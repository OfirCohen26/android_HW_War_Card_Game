package com.example.myapplication1hw;

import android.content.Context;
import android.content.SharedPreferences;

public class My_SPV {
    public interface KEYS {
        public static final String ALL_PLAYER = "ALL_PLAYER";
    }

    private static My_SPV instance;
    private SharedPreferences prefs;

    public static My_SPV getInstance() {
        return instance;
    }

    private My_SPV(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences("MY_SP", Context.MODE_PRIVATE);
    }

    public static My_SPV init(Context context) {
        if (instance == null)
            instance = new My_SPV(context);
        return instance;
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String def) {
        return prefs.getString(key, def);
    }
}
