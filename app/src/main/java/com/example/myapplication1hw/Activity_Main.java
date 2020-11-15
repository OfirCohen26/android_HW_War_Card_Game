package com.example.myapplication1hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.media.MediaPlayer;

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
    private ImageView main_IMG_player1_card;
    private ImageView main_IMG_player2_card;

    private int player1Score = 0;
    private int player2Score = 0;
    private int numOfRounds = 0;
    private int theWinner = 0;

    private final Random RANDOM = new Random();

    Card firstCard;
    Card secondCard;

    Color red = Color.RED;
    Color black = Color.BLACK;
    Shape diamond = Shape.DIAMOND;
    Shape heart = Shape.HEART;
    Shape clubs = Shape.CLUBS;
    Shape spades = Shape.SPADES;

    List<Card> cards;

    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cards = new ArrayList<Card>();
        findViews();
        initImage(R.drawable.inverted_card,main_IMG_player1_card);
        initImage(R.drawable.inverted_card,main_IMG_player2_card);

        createListOfCards();
        //Start the game
        addClickListeners();
    }

    private void createSound(int source) {
        mediaPlayer = MediaPlayer.create(this, source);
        mediaPlayer.start();
    }

    private void initImage(int imageSource, ImageView imageView) {
        imageView.setImageResource(imageSource);
    }

    private void createListOfCards() {
        addToCards(cards, red.name(), diamond.name());
        addToCards(cards, red.name(), heart.name());
//        addToCards(cards,black.name(),clubs.name());
//        addToCards(cards,black.name(),spades.name());
    }

    private void addToCards(List<Card> cards, String color, String shape) {
        for (int i = 1; i <= 13; i++) {
            Card temp = new Card(i, color, shape);
            cards.add(temp);
        }
    }

    private void addClickListeners() {
        main_BTN_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numOfRounds < 13) { // only 26 rounds until the game is over
                    createSound(R.raw.card_game_sound_effect);
                    firstCard = new Card();
                    secondCard = new Card();
                    firstCard = cards.get(randomCardNumber(cards.size()));
                    cards.remove(firstCard);
                    secondCard = cards.get(randomCardNumber(cards.size()));
                    cards.remove(secondCard);
                    //Show cards results on screen
                    showCardsAndFindWinnerOfRound(firstCard, secondCard);
                    numOfRounds++;
                } else {
                    findWinnerOfTheGame();
                }
            }
        });
    }

    private void invertCards() {
        int CardImage = getResources().getIdentifier(INVERTED_CARD, TYPE, FOLDER);
        main_IMG_player1_card.setImageResource(CardImage);
        main_IMG_player2_card.setImageResource(CardImage);
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
    private void findWinnerOfTheGame() {
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
        } else {
            player1Score++;
            player2Score++;
        }
        main_player1_LBL_result.setText("" + player1Score);
        main_player2_LBL_result.setText("" + player2Score);
    }

    // Show selected Card the find the winner of the round
    private void showCardsAndFindWinnerOfRound(final Card card1, final Card card2) {
        int firstCardImage = getResources().getIdentifier(card1.getShape().toLowerCase() + "_" + card1.getColor().toLowerCase() + "_" + card1.getValue(), TYPE, FOLDER);
        main_IMG_player1_card.setImageResource(firstCardImage);
        int secondCardImage = getResources().getIdentifier(card2.getShape().toLowerCase() + "_" + card2.getColor().toLowerCase() + "_" + card2.getValue(), TYPE, FOLDER);
        main_IMG_player2_card.setImageResource(secondCardImage);
        checkRoundWinnerCard(firstCard, secondCard);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                invertCards();
            }
        }, 1000);

    }

    private void findViews() {
        main_player1_LBL_result = findViewById(R.id.main_player1_LBL_result);
        main_player2_LBL_result = findViewById(R.id.main_player2_LBL_result);
        main_BTN_startGame = findViewById(R.id.main_BTN_startGame);
        main_LAY_cards_container = findViewById(R.id.main_LAY_cards_container);
        main_IMG_player1_card = findViewById(R.id.main_IMG_player1_card);
        main_IMG_player2_card = findViewById(R.id.main_IMG_player2_card);
    }
}
