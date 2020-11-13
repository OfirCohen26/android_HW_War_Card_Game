package com.example.myapplication1hw;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.Random;

public class Card {

    final int minNumOfCard = 1;
    final int maNumOxfCard = 13;
    private static Color[] colors = {Color.BLACK, Color.RED};
    private static Shape[] shapes = {Shape.DIAMOND, Shape.HEART, Shape.CLUBS, Shape.LEAF};
    private int value;
    private String color;
    private String shape;


    public Card(Random random){
        raffleRandomValues(random);
    }

    private void raffleRandomValues(Random random) {
        this.value = random.nextInt((maNumOxfCard - minNumOfCard) + 1) + minNumOfCard;
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

    public void setColor(String color) {
        this.color = color;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public void setValue(int value) {
        this.value = value;
    }
}


