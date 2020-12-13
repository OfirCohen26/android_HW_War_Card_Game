package com.example.myapplication1hw;

import android.content.Context;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;
import android.location.LocationManager;
import android.Manifest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Open_Screen extends AppCompatActivity {
    private Button openScreen_BTN_startGame;
    private Button openScreen_BTN_Top10;
    private MediaPlayer mediaPlayer;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open__screen);
        findViews();
        playSound();
        addClickListeners();
        checkIfGPSOn();
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

    private void checkIfGPSOn() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            alertMessageNoGPS();
        } else {
            fetchLocation();
        }
    }

    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
    }

    private void alertMessageNoGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Your GPS seems to be disable, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        final AlertDialog alert = builder.create();
        alert.show();
    }


    private void findViews() {
        openScreen_BTN_startGame = findViewById(R.id.openScreen_BTN_startGame);
        openScreen_BTN_Top10 =  findViewById(R.id.openScreen_BTN_Top10);
    }
    @Override
    protected void onPause() {
        mediaPlayer.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mediaPlayer.start();
        super.onResume();
    }

    private void playSound() {
        mediaPlayer = MediaPlayer.create(this, R.raw.waterphone);
        mediaPlayer.setLooping(true);
    }


}