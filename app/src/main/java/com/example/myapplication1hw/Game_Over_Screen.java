package com.example.myapplication1hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Game_Over_Screen extends AppCompatActivity {

    //Setting variables
    private int winnerScore;
    public static final String EXTRA_KEY_WINNER = "EXTRA_KEY_WINNER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__over__screen);
        findViews();

        // get the Player that Won
        int winnerScore = getIntent().getIntExtra(EXTRA_KEY_WINNER, -1);
//        calculator_LBL_result.setText("" + previousCount);
    }

    private void findViews() {

    }
}