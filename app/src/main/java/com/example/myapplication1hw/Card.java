package com.example.myapplication1hw;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.Random;

public class Card {

    private static Color[] colors = {Color.BLACK, Color.RED};
    private static Shape[] shapes = {Shape.DIAMOND, Shape.HEART, Shape.CLUBS, Shape.LEAF};
    final int min = 1;
    final int max = 52;
    private int value;
    private String color;
    private String shape;


    public Card(Random random){
        raffleRandomValues(random);
    }

    private void raffleRandomValues(Random random) {
        this.value = random.nextInt((max - min) + 1) + min;
        this.color = colors[random.nextInt(1 - 0) + 1 + 0].name();
        this.shape = shapes[random.nextInt(3-0) + 1 + 0].name();
    }


    public int getValue(){
        return this.value;
    }

    public String getColor(){
        return this.color;
    }

    public String getShape(){
        return this.shape;
    }
}


