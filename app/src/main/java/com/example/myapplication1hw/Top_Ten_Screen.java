package com.example.myapplication1hw;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageView;

public class Top_Ten_Screen extends Activity_Base {

    private ImageView top10_IMG_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten_screen);
        findViews();
        addClickListeners();
    }

    private void addClickListeners() {
        top10_IMG_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMenu();
            }
        });
    }

    private void findViews() {
        top10_IMG_back =findViewById(R.id.top10_IMG_back);
    }

    private void backToMenu() {
        finish();
    }
}


