package com.example.myapplication1hw;

import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class Game_Over_Screen extends Activity_Base {

    //Setting variables
    public static final String EXTRA_KEY_WINNER = "EXTRA_KEY_WINNER";
    private TextView gameOver_LBL_Score;
    private ImageView gameOver_BTN_OK;
    private EditText gameOver_EDT_playerName;
    private ImageView gameOver_IMG_VIEW_gameOver;

    private Player_Info player;
    Location currentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__over__screen);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Game_Over_Screen.this);
        findViews();
        Glide.with(this).load(getResources()
                .getIdentifier("game_over", "drawable","com.example.myapplication1hw"))
                .into(gameOver_IMG_VIEW_gameOver);
        getWinnerInformation();
        getLocation();
        addClickListeners();
    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        currentLocation = location;

                        player.setLat(currentLocation.getLatitude());
                        player.setLon(currentLocation.getLongitude());
                    }
                }
            });
        }
    }


    private void getWinnerInformation() {
//        String winner = getIntent().getStringExtra("winner");
        Bundle data = getIntent().getExtras();
        player = (Player_Info) data.getSerializable("player");
        int score = player.getScore();
        gameOver_LBL_Score.setText("Score: "+ score);
    }

    private void addClickListeners() {
        gameOver_BTN_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmNameAndBackToMenu();
            }
        });
    }


   private void confirmNameAndBackToMenu(){
       String playerName = gameOver_EDT_playerName.getText().toString();
       if (playerName.equals("") || playerName.isEmpty()) {
           playerName = "Player";
       }
       player.setName(playerName);
       handleHighScore();
       Intent intent = new Intent(this, Open_Screen.class);
       startActivity(intent);
       finish();
   }

    private void findViews() {
        gameOver_LBL_Score = findViewById(R.id.gameOver_LBL_Score);
        gameOver_BTN_OK = findViewById(R.id.gameOver_BTN_OK);
        gameOver_EDT_playerName = findViewById(R.id.gameOver_EDT_playerName);
        gameOver_IMG_VIEW_gameOver = findViewById(R.id.gameOver_IMG_VIEW_gameOver);
    }

    private void handleHighScore() {
        ArrayList<Player_Info> players;
        High_Score highScore = new High_Score();
        players = highScore.readScores();
        highScore.addPlayer(player, players);
    }
}
