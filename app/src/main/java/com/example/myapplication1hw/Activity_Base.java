package com.example.myapplication1hw;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Base extends AppCompatActivity  {
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            MyScreenUtils.hideSystemUI(this);
        }
    }

}
