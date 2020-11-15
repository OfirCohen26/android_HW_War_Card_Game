package com.example.myapplication1hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Game_Over_Screen extends AppCompatActivity {

    //Setting variables
    private String winner;
    public static final String EXTRA_KEY_WINNER = "EXTRA_KEY_WINNER";
    private TextView main_LBL_theWinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__over__screen);
        findViews();

        // get the Player that Won
       winner = getIntent().getStringExtra(EXTRA_KEY_WINNER);
       if (winner.equals("Draw"))
             main_LBL_theWinner.setText("No Winner it's a "+winner);
       else
        main_LBL_theWinner.setText("The Winner is " + winner);
    }

    private void findViews() {
        main_LBL_theWinner = findViewById(R.id.main_LBL_theWinner);
    }
}