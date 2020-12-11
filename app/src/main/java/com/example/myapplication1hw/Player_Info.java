package com.example.myapplication1hw;

import java.io.Serializable;

public class Player_Info implements Serializable, Comparable<Player_Info> {
    private String name;
    private int score;
    private double lat;
    private double lon;

    public Player_Info() { }

    public Player_Info(String name, int score, double lat, double lon) {
        this.name = name;
        this.score = 0;
        this.lat = lat;
        this.lon = lon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                "score=" + score +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }

    @Override
    public int compareTo(Player_Info player) {
        if (this.score > player.score) {
            return 1;
        } else if (this.score < player.score) {
            return -1;
        } else {
            return 0;
        }
    }
}
