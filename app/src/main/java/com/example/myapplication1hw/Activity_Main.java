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
    final int NUMBER_OF_CARD_DECK = 26;
    final int SUIT_SIZE = 13;
    final String INVERTED_CARD = "inverted_card";
    private final Random RANDOM = new Random();
    private MediaPlayer mediaPlayer;

    private TextView main_player1_LBL_result;
    private TextView main_player2_LBL_result;
    private ImageView main_BTN_startGame;
    private LinearLayout main_LAY_cards_container;
    private ImageView main_IMG_player1_card;
    private ImageView main_IMG_player2_card;

    private List<Card> cards;
    private Card firstCard;
    private Card secondCard;

    int player1Score = 0;
    int player2Score = 0;
    private int numOfRounds = 0;
    private int theWinner = 0;

    Color red = Color.RED;
    Color black = Color.BLACK;
    Shape diamond = Shape.DIAMOND;
    Shape heart = Shape.HEART;
    Shape clubs = Shape.CLUBS;
    Shape spades = Shape.SPADES;

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

    private void playSound(int rawSound) {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.reset();
                mediaPlayer.release();
            } catch (Exception ex) { }
        }

        mediaPlayer = MediaPlayer.create(this, rawSound);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });
        mediaPlayer.start();
    }


    private void initImage(int imageSource, ImageView imageView) {
        imageView.setImageResource(imageSource);
    }

    private void createListOfCards() {
        addToCards(cards, red.name(), diamond.name());
        addToCards(cards, red.name(), heart.name());
        addToCards(cards, black.name(), clubs.name());
        addToCards(cards,black.name(),spades.name());
    }

    private void addToCards(List<Card> cards, String color, String shape) {
        for (int i = 1; i <= SUIT_SIZE; i++) {
            Card temp = new Card(i, color, shape);
            cards.add(temp);
        }
    }

    private void addClickListeners() {
        main_BTN_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_BTN_startGame.setEnabled(false);
                playSound(R.raw.card_game_sound_effect);
                firstCard = new Card();
                secondCard = new Card();
                firstCard = cards.get(randomCardNumber(cards.size()));
                cards.remove(firstCard);
                secondCard = cards.get(randomCardNumber(cards.size()));
                cards.remove(secondCard);
                //Show cards results on screen
                showCardsAndFindWinnerOfRound(firstCard, secondCard);
                numOfRounds++;
                if(numOfRounds == NUMBER_OF_CARD_DECK){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findWinnerOfTheGame();
                        }
                    }, 1000);
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
            myIntent.putExtra(Game_Over_Screen.EXTRA_KEY_WINNER, "Bob");
        else if (theWinner == 2)
            myIntent.putExtra(Game_Over_Screen.EXTRA_KEY_WINNER, "Khloe");
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
                main_BTN_startGame.setEnabled(true);
            }
        }, 1300);

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
