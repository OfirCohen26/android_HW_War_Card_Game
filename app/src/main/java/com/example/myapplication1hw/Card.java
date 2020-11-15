package com.example.myapplication1hw;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.Random;

public class Card implements CardsFunc {

    private int value;
    private String color;
    private String shape;

    public Card() {
    }

    public Card(int value, String color, String shape) {
        this.setShape(shape);
        this.setColor(color);
        this.setValue(value);
    }

    public int getValue() {
        return this.value;
    }

    public String getColor() {
        return this.color;
    }

    public String getShape() {
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