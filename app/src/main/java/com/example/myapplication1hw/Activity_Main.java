package com.example.myapplication1hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Activity_Main extends AppCompatActivity {

    //Setting variables
    final String FOLDER = "com.example.myapplication1hw";
    final String TYPE = "drawable";
    final int MIN_NUM_OF_CARD = 1;
    final int MAX_NUM_OF_CARD = 12;
    final int MIN_NUM_OF_COLOR = 0;
    final int MAX_NUM_OF_COLOR = 1;
    final int MIN_NUM_OF_SHAPE_RED = 0;
    final int MAX_NUM_OF_SHAPE_RED = 1;
    final int MIN_NUM_OF_SHAPE_BLACK = 2;
    final int MAX_NUM_OF_SHAPE_BLACK = 3;
    final String INVERTED_CARD = "inverted_card";

    private TextView main_player1_LBL_result;
    private TextView main_player2_LBL_result;
    private ImageView main_BTN_startGame;
    private LinearLayout main_LAY_cards_container;
    private ImageView main_IMG_VIEW_player1_card;
    private ImageView main_IMG_VIEW_player2_card;

    private int player1Score = 0;
    private int player2Score = 0;
    private int numOfRounds = 0;
    private int theWinner = 0;

    private Random RANDOM = new Random();

    Card firstCard;
    Card secondCard;

    private static Color[] colors = {Color.BLACK, Color.RED};
    private static Shape[] shapes = {Shape.DIAMOND, Shape.HEART, Shape.SPADES, Shape.CLUBS};

    List<Card> cards = new ArrayList<Card>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstCard = new Card();
        secondCard = new Card();
        findViews();
        createListOfCards();
        addClickListeners();
    }

    private void createListOfCards() {
        for (int i = 1; i <= 12; i++) {
            cards.add(new Card(i, "RED", "DIAMOND"));
        }
    }

    private void addClickListeners() {
        main_BTN_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numOfRounds < 6) { // only 26 rounds until the game is over
                    // ruffle 2 Cards
                    raffleCardsAndShow();
//                     check if the cards are different
                    while (!ifEquals()) {// while the cards are equals ruffle again
                        raffleCardsAndShow();
                    }
//                    // check which player is the Winner Of this round
//                  invertCards();
                    checkRoundWinnerCard();
                    numOfRounds++;
                } else {
                    if (player1Score > player2Score)
                        theWinner = 1; // player 1 won
                    else if (player1Score < player2Score)
                        theWinner = 2; // player 2 won
                    else theWinner = 0; // draw
                    openGameOverScreen(); // send the Winner
                }
            }
        });
    }


    private void openGameOverScreen() {
        Intent myIntent = new Intent(Activity_Main.this, Game_Over_Screen.class);
        if (theWinner == 1)
            myIntent.putExtra(Game_Over_Screen.EXTRA_KEY_WINNER, "Player1");
        else if (theWinner == 2)
            myIntent.putExtra(Game_Over_Screen.EXTRA_KEY_WINNER, "Player2");
        else
            myIntent.putExtra(Game_Over_Screen.EXTRA_KEY_WINNER, "Draw");
        startActivity(myIntent);
        finish();
    }

    private void invertCards() {
        int invertCardImage = getResources().getIdentifier("inverted_card", TYPE, FOLDER);
        main_IMG_VIEW_player1_card.setImageResource(invertCardImage);
        main_IMG_VIEW_player2_card.setImageResource(invertCardImage);
    }


    private void checkRoundWinnerCard() {
        //Check who win according to the value
        if (firstCard.getValue() > secondCard.getValue()) {
            player1Score++;
        } else if (firstCard.getValue() < secondCard.getValue()) {
            player2Score++;
        }
//        else {
//            player1Score++;
//            player2Score++;
//        }
        main_player1_LBL_result.setText("" + player1Score);
        main_player2_LBL_result.setText("" + player2Score);
    }

    // Create random Card
    private void raffleRandomValues(Card card) {
        card.setValue(RANDOM.nextInt((MAX_NUM_OF_CARD - MIN_NUM_OF_CARD) + 1) + MIN_NUM_OF_CARD);
        card.setColor(colors[1].name());
        card.setShape(shapes[0].name());

//        card.setColor(colors[RANDOM.nextInt(MAX_NUM_OF_COLOR - MIN_NUM_OF_COLOR) + 1 +MIN_NUM_OF_COLOR].name());
//        if(card.getColor().equals(Color.RED.name()))
//            card.setShape(shapes[RANDOM.nextInt(MAX_NUM_OF_SHAPE_RED - MIN_NUM_OF_SHAPE_RED) + 1 + MIN_NUM_OF_SHAPE_RED].name());
//        else
//            card.setShape(shapes[RANDOM.nextInt(MAX_NUM_OF_SHAPE_BLACK - MIN_NUM_OF_SHAPE_BLACK) + 1 + MIN_NUM_OF_SHAPE_BLACK].name());
        }
    private void raffleCardsAndShow() {
        raffleRandomValues(firstCard);
        Log.d("MYINT", "value: " + firstCard.getValue());
        Log.d("pttt2", firstCard.getShape());
        Log.d("pttt9", firstCard.getColor());
        raffleRandomValues(secondCard);
        Log.d("MYINT2", "value: " + secondCard.getValue());
        Log.d("pttt3", secondCard.getShape());
        Log.d("pttt8", secondCard.getColor());
        showCards();
//
//        int a = cards.indexOf(firstCard);
//        Log.d("aray", "" +a);

    }

    private void showCards() {
        int firstCardImage = getResources().getIdentifier(firstCard.getShape().toLowerCase() + "_" + firstCard.getColor().toLowerCase() + "_" + firstCard.getValue(), TYPE, FOLDER);
        main_IMG_VIEW_player1_card.setImageResource(firstCardImage);

        int secondCardImage = getResources().getIdentifier(secondCard.getShape().toLowerCase() + "_" + secondCard.getColor().toLowerCase() + "_" + secondCard.getValue(), TYPE, FOLDER);
        main_IMG_VIEW_player2_card.setImageResource(secondCardImage);
    }


    // Check if the ruffled cards are equal
    private boolean ifEquals(){
        if(firstCard.getValue() != secondCard.getValue()){
//            ||
//                !firstCard.getColor().equals(secondCard.getColor() )||
//               !firstCard.getShape().equals(secondCard.getShape())) {
            // not the same shape
            Log.d("not again", "ruffled");
            return true;
        }
        Log.d("again", "ruffled");
        return false; // the Cards are the not the same - need to ruffle again
    }

    private void findViews() {
        main_player1_LBL_result = findViewById(R.id.main_player1_LBL_result);
        main_player2_LBL_result = findViewById(R.id.main_player2_LBL_result);
        main_BTN_startGame = findViewById(R.id.main_BTN_startGame);
        main_LAY_cards_container = findViewById(R.id.main_LAY_cards_container);
        main_IMG_VIEW_player1_card = findViewById(R.id.main_IMG_VIEW_player1_card);
        main_IMG_VIEW_player2_card = findViewById(R.id.main_IMG_VIEW_player2_card);
    }
}

//
//    @Override
//    protected void onStart() {
//        Log.d("pttt", "onStart");
//        super.onStart();
//    }
//
//    @Override
//    protected void onResume() {
//        Log.d("pttt", "onResume");
//        super.onResume();
//
//    }
//
//    @Override
//    protected void onPause() {
//        Log.d("pttt", "onPause");
//        super.onPause();
//    }
//
//    @Override
//    protected void onStop() {
//        Log.d("pttt", "onStop");
//        super.onStop();
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        Log.d("pttt", "onDestroy");
//        super.onDestroy();
//    }
//}
