package com.example.myapplication1hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Setting variables
    final String FOLDER = "com.example.myapplication1hw";
    final String TYPE = "drawable";
    private TextView main_player1_LBL_result;
    private TextView main_player2_LBL_result;
    private ImageButton main_BTN_startGame;
    private LinearLayout main_LAY_cards_container;
    private ImageView main_IMG_VIEW_player1_card;
    private ImageView main_IMG_VIEW_player2_card;
    private ImageView[] cards = new ImageView[2];
    private Card[] cardsInfo = new Card[2];
    private int player1Result = 0;
    private int player2Result = 0;
    private int numOfRounds = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        addClickListeners();

    }

    private void findViews() {
        main_player1_LBL_result = findViewById(R.id.main_player1_LBL_result);
        main_player2_LBL_result = findViewById(R.id.main_player2_LBL_result);
        main_BTN_startGame = findViewById(R.id.main_BTN_startGame);
        main_LAY_cards_container = findViewById(R.id.main_LAY_cards_container);
        main_IMG_VIEW_player1_card = findViewById(R.id.main_IMG_VIEW_player1_card);
        main_IMG_VIEW_player2_card = findViewById(R.id.main_IMG_VIEW_player2_card);

        cards[0] = main_IMG_VIEW_player1_card;
        cards[1] = main_IMG_VIEW_player2_card;

    }

    private void addClickListeners() {

        main_BTN_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                raffleCards();
                checkRoundWinnerCard();
                numOfRounds++;
            }
        });
    }

    private void checkRoundWinnerCard() {
        //Check who win according to the value
        if (cardsInfo[0].getValue() > cardsInfo[1].getValue()) {
            player1Result++;
        } else {
            player2Result++;
        }
        main_player1_LBL_result.setText(player1Result);
        main_player2_LBL_result.setText(player2Result);
    }

    private void raffleCards() {
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            //Raffle random number, color and shape
            Card card = new Card(random);
            cardsInfo[i] = card;
            //Set the image of the cards to the raffled values
            int cardImage = getResources().getIdentifier(card.getShape() + "_" + card.getColor() + "_" + card.getValue(), TYPE, FOLDER);
            cards[i].setImageResource(cardImage);
        }
    }

    @Override
    protected void onStart() {
        Log.d("pttt", "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("pttt", "onResume");
        super.onResume();

    }

    @Override
    protected void onPause() {
        Log.d("pttt", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("pttt", "onStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d("pttt", "onDestroy");
        super.onDestroy();
    }
}
