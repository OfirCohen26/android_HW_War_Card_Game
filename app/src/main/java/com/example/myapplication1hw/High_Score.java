package com.example.myapplication1hw;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class High_Score implements Variables {
    private ArrayList<Player_Info> allPlayersInfo;

    public High_Score() {
    }

    public ArrayList<Player_Info> getAllPlayersInfo() {
        return allPlayersInfo;
    }

    //Read gson with player in top to player array
    public ArrayList<Player_Info> readScores() {
        //Get json from SharedPreferences
        String jsonString = My_SPV.getInstance().getString(My_SPV.KEYS.ALL_PLAYER, null);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Player_Info>>() {
        }.getType();

        //Save gson to player array
        if ((allPlayersInfo = gson.fromJson(jsonString, type)) == null)
            allPlayersInfo = new ArrayList<>();

        return allPlayersInfo;
    }

    //Write player array to gson after updating and sav in SharedPreferences
    public void writeScore(ArrayList<Player_Info> playersInfo) {
        String jsonStringAllPlayers;
        Gson gson = new Gson();
        jsonStringAllPlayers = gson.toJson(playersInfo);

        My_SPV.getInstance().putString(My_SPV.KEYS.ALL_PLAYER, jsonStringAllPlayers);
    }

    //Add / Update array of Players
    public void addPlayer(Player_Info newPlayerInfo, ArrayList<Player_Info> playersInfo) {
        int index;
        if (playersInfo == null || playersInfo.size() < MAX_SIZE) {
            playersInfo.add(newPlayerInfo);
        } else {
            index = findIndexToInsert(newPlayerInfo, playersInfo);
            if (index != NOT_FOUND) {
                playersInfo.set(index, newPlayerInfo);
            }
        }
        //Sort the player in array by score
        Collections.sort(playersInfo, Collections.reverseOrder());
        writeScore(playersInfo);
    }


    public int findIndexToInsert(Player_Info newPlayerInfo, ArrayList<Player_Info> playersInfo) {
        for (int index = 0; index < playersInfo.size(); index++) {
            if (newPlayerInfo.compareTo(playersInfo.get(index)) >= 0) {
                return index;
            }
        }
        return NOT_FOUND;
    }
}

