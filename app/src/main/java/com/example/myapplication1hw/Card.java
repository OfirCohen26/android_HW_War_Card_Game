package com.example.myapplication1hw;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.Random;

public class Card implements CardsFunc {

    private int value;
    private String color;
    private String shape;
    private Boolean active;

    public Card() {
    }

    public Card(int value, String color, String shape, Boolean active) {
        this.setShape(shape);
        this.setColor(color);
        this.setValue(value);
        this.setActive(active);
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

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    //equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Card))
            return false;
        Card otherCard = (Card) o;
        return getValue()== otherCard.getValue() && getShape().equals(otherCard.getShape()) && getColor().equals(otherCard.getColor());
    }

    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", color='" + color + '\'' +
                ", shape='" + shape + '\'' +
                ", active=" + active +
                '}';
    }
}
