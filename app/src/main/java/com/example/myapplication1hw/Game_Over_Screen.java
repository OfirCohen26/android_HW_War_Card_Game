package com.example.myapplication1hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Game_Over_Screen extends AppCompatActivity {

    //Setting variables
    private String winner;
    public static final String EXTRA_KEY_WINNER = "EXTRA_KEY_WINNER";
    private TextView gameOver_LBL_theWinner;
    private ImageView gameOver_BTN_newGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__over__screen);
        findViews();
        // get the Player that Won
        getInformation();

        addClickListeners();
    }

    private void getInformation() {
        winner = getIntent().getStringExtra(EXTRA_KEY_WINNER);
        if (winner.equals("Draw"))
            gameOver_LBL_theWinner.setText("No Winner it's a "+winner);
        else
            gameOver_LBL_theWinner.setText("The Winner is " + winner);
    }

    private void addClickListeners() {
        gameOver_BTN_newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });
    }


   private void newGame(){
       Intent intent = new Intent(this, Activity_Main.class);
       finish();
       startActivity(intent);
    }

    private void findViews() {
        gameOver_LBL_theWinner = findViewById(R.id.gameOver_LBL_theWinner);
        gameOver_BTN_newGame = findViewById(R.id.gameOver_BTN_newGame);
    }
}