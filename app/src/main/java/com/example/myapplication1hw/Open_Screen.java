package com.example.myapplication1hw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;

public class Open_Screen extends Activity_Base {

    private Button openScreen_BTN_startGame;
    private Button openScreen_BTN_Top10;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open__screen);
        findViews();
        addClickListeners();

    }


    private void addClickListeners() {
        openScreen_BTN_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });

        openScreen_BTN_Top10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topTen();
            }
        });
    }

    private void newGame(){
        Intent intent = new Intent(this, Activity_Main.class);
        startActivity(intent);
    }

    private void topTen(){
        Intent intent = new Intent(Open_Screen.this, Top_Ten_Screen.class);
        startActivity(intent);
    }

    private void findViews() {
        openScreen_BTN_startGame = findViewById(R.id.openScreen_BTN_startGame);
        openScreen_BTN_Top10 =  findViewById(R.id.openScreen_BTN_Top10);
    }

//    private void playSound() {
////        mediaPlayer = MediaPlayer.create(this, R.raw.hlicopter);
//        mediaPlayer.setLooping(true);
//    }

//    @Override
//    protected void onPause() {
//        mediaPlayer.pause();
//        super.onPause();
//    }
//
//    @Override
//    protected void onResume() {
////        mediaPlayer.start();
//        super.onResume();
//    }

}