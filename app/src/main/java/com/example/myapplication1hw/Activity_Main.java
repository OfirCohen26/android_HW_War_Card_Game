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
    final int NUMBER_OF_CARDS = 52;

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

    private final Random RANDOM = new Random();

    Card card1;
    Card card2;
    private final Color[] colors = {Color.BLACK, Color.RED};
    private final Shape[] shapes = {Shape.DIAMOND, Shape.HEART, Shape.SPADES, Shape.CLUBS};

    List<Card> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        createListOfCards();
        addClickListeners();
    }
//
    private void createListOfCards() {
        cards = new ArrayList<Card>();
        for (int i = 1; i <= 12; i++) {
            Card temp = new Card(i, "RED", "DIAMOND");
            cards.add(temp);
        }
    }

    private void addClickListeners() {
        main_BTN_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numOfRounds < 6) { // only 26 rounds until the game is over
                    card1 = new Card();
                    card2 = new Card();
                    card1 = cards.get(randomCardNumber(cards.size()));
                    cards.remove(card1);
                    card2 = cards.get(randomCardNumber(cards.size()));
                    cards.remove(card2);
                    showCards(card1,card2);
                    checkRoundWinnerCard(card1,card2);
                    numOfRounds++;
                } else {
                    findWinnerOfTheGame();
                }
            }
        });
    }

    private void invertCards(){
        int CardImage = getResources().getIdentifier(INVERTED_CARD, TYPE, FOLDER);
        main_IMG_VIEW_player1_card.setImageResource(CardImage);
        main_IMG_VIEW_player2_card.setImageResource(CardImage);

    }

    private int randomCardNumber(int num) {
        return RANDOM.nextInt(num);
    }

    //Send the Winner to Game_Over_Screen
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
    // find the Winner Of The game
    private void findWinnerOfTheGame(){
        if (player1Score > player2Score)
            theWinner = 1; // player 1 won
        else if (player1Score < player2Score)
            theWinner = 2; // player 2 won
        else theWinner = 0; // draw
        openGameOverScreen(); // send the Winner
    }
    //Check who win according to the value
    private void checkRoundWinnerCard(Card card1, Card card2) {
        if (card1.getValue() > card2.getValue()) {
            player1Score++;
        } else if (card1.getValue() < card2.getValue()) {
            player2Score++;
        } else{
            player1Score++;
            player2Score++;
        }
        main_player1_LBL_result.setText("" + player1Score);
        main_player2_LBL_result.setText("" + player2Score);
    }
    // Show selected Cards
    private void showCards(Card card1, Card card2) {
        int firstCardImage = getResources().getIdentifier(card1.getShape().toLowerCase() + "_" + card1.getColor().toLowerCase() + "_" + card1.getValue(), TYPE, FOLDER);
        main_IMG_VIEW_player1_card.setImageResource(firstCardImage);

        int secondCardImage = getResources().getIdentifier(card2.getShape().toLowerCase() + "_" + card2.getColor().toLowerCase() + "_" + card2.getValue(), TYPE, FOLDER);
        main_IMG_VIEW_player2_card.setImageResource(secondCardImage);
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
