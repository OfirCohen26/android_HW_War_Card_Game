package com.example.myapplication1hw;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;
import android.media.MediaPlayer;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Activity_Main extends Activity_Base implements Variables {
    private final Random RANDOM = new Random();

    private MediaPlayer mediaPlayer;
    private Timer time;
    private boolean firstGame = true;

    private TextView main_player1_LBL_result;
    private TextView main_player2_LBL_result;
    private ImageView main_BTN_startGame;
    private LinearLayout main_LAY_cards_container;
    private ImageView main_IMG_player1_card;
    private ImageView main_IMG_player2_card;
    private ProgressBar main_PGB_lifeOfFirstPlayer;

    private List<Card> cards;
    private Card firstCard;
    private Card secondCard;
    private Player_Info player;

    int player1Score = 0;
    int player2Score = 0;
    private int numOfRounds = 0;

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
        time = new Timer();
        player = new Player_Info();
        findViews();
        setImage(R.drawable.inverted_card, main_IMG_player1_card);
        setImage(R.drawable.inverted_card, main_IMG_player2_card);
        createListOfCards();
        progressBarColor();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Start the game
                playWithTimer(time);

            }
        }, DELAY);
    }

    private void playWithTimer(Timer timer) {
        //Use timer to play the game
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBarAnimation();
                        firstGame = false;
                        playSound(R.raw.card_game_sound_effect);
                        firstCard = new Card();
                        secondCard = new Card();
                        firstCard = cards.get(randomCardNumber(cards.size()));
                        cards.remove(firstCard);
                        secondCard = cards.get(randomCardNumber(cards.size()));
                        cards.remove(secondCard);
                        showCardsAndFindWinnerOfRound(firstCard, secondCard);
                        numOfRounds++;
                        if(numOfRounds == NUMBER_OF_CARD_DECK){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    time.cancel();
                                    findWinnerOfTheGame();
                                }
                             }, DELAY);
                        }
                    }
                });
            }
        }, ZERO, PERIOD);
    }


    private void progressBarColor(){
        Drawable progressDrawable = main_PGB_lifeOfFirstPlayer.getProgressDrawable().mutate();
        progressDrawable.setColorFilter((0xFFFF0000), android.graphics.PorterDuff.Mode.SRC_IN);
        main_PGB_lifeOfFirstPlayer.setProgressDrawable(progressDrawable);
    }

    private void progressBarAnimation(){
        ObjectAnimator animation = ObjectAnimator.ofInt(main_PGB_lifeOfFirstPlayer, "progress", PROGRESS_VALUE, ZERO);
        animation.setDuration(DURATION);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }

    private void invertCards() {
        int CardImage = getResources().getIdentifier(INVERTED_CARD, TYPE, FOLDER);
        setImage(CardImage,main_IMG_player1_card);
        setImage(CardImage,main_IMG_player2_card);
    }

    private int randomCardNumber(int num) {
        return RANDOM.nextInt(num);
    }

    //Send the Winner to Game_Over_Screen
    private void openGameOverScreen() {
        setDefaultLocation();
        Intent intent = new Intent(this, Game_Over_Screen.class);
        intent.putExtra("player", player);
        startActivity(intent);
        finish();
    }
    // find the Winner Of The game
    private void findWinnerOfTheGame() {
        if (player1Score > player2Score) {
            player.setScore(player1Score);
        }
        else {
            player.setScore(player2Score);
        }
        openGameOverScreen(); // send the Winner
    }

    //Check who win according to the value
    private void checkRoundWinnerCard(Card card1, Card card2) {
        if (card1.getValue() > card2.getValue()) {
            player1Score++;
        } else if (card1.getValue() < card2.getValue()) {
            player2Score++;
        }
        main_player1_LBL_result.setText("" + player1Score);
        main_player2_LBL_result.setText("" + player2Score);
    }

    // Show selected Card the find the winner of the round
    private void showCardsAndFindWinnerOfRound(final Card card1, final Card card2) {
        int firstCardImage = getResources().getIdentifier(card1.getShape().toLowerCase() + "_" + card1.getColor().toLowerCase() + "_" + card1.getValue(), TYPE, FOLDER);
        setImage(firstCardImage,main_IMG_player1_card);
        int secondCardImage = getResources().getIdentifier(card2.getShape().toLowerCase() + "_" + card2.getColor().toLowerCase() + "_" + card2.getValue(), TYPE, FOLDER);
        setImage(secondCardImage,main_IMG_player2_card);
        checkRoundWinnerCard(firstCard, secondCard);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                invertCards();
//                main_BTN_startGame.setEnabled(true);
            }
        }, DELAY);

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

    public void setImage(int id,ImageView imageView) {
        Glide
                .with(this)
                .load(id)
                .into(imageView);
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

    private void findViews() {
        main_player1_LBL_result = findViewById(R.id.main_player1_LBL_result);
        main_player2_LBL_result = findViewById(R.id.main_player2_LBL_result);
        main_BTN_startGame = findViewById(R.id.main_BTN_startGame);
        main_LAY_cards_container = findViewById(R.id.main_LAY_cards_container);
        main_IMG_player1_card = findViewById(R.id.main_IMG_player1_card);
        main_IMG_player2_card = findViewById(R.id.main_IMG_player2_card);
        main_PGB_lifeOfFirstPlayer = findViewById(R.id.main_PGB_lifeOfFirstPlayer);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!firstGame) {
            time = new Timer();
            playWithTimer(time);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        time.cancel();
    }


    private void setDefaultLocation() {
        player.setLat(45.833398902478265);
        player.setLon(6.865132016610975);
    }


    //    private void addClickListeners() {
//        main_BTN_startGame.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                main_BTN_startGame.setEnabled(false);
//                playSound(R.raw.card_game_sound_effect);
//                firstCard = new Card();
//                secondCard = new Card();
//                firstCard = cards.get(randomCardNumber(cards.size()));
//                cards.remove(firstCard);
//                secondCard = cards.get(randomCardNumber(cards.size()));
//                cards.remove(secondCard);
//                //Show cards results on screen
//                showCardsAndFindWinnerOfRound(firstCard, secondCard);
//                numOfRounds++;
//                if(numOfRounds == NUMBER_OF_CARD_DECK){
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            findWinnerOfTheGame();
//                        }
//                    }, 1000);
//                }
//            }
//        });
//    }
}
