package com.example.myapplication1hw;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Base extends AppCompatActivity  {
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            My_Screen_Utils.hideSystemUI(this);
        }
    }

}
