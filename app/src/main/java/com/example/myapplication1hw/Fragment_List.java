package com.example.myapplication1hw;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment_List extends Fragment{

    private View rootView;
    private Recycler_Adapter adapter;
    private High_Score highScore;
    private RecyclerView.LayoutManager layoutManager;
    private Fragment_High_Score_List listener;
    private ArrayList<Player_Info> players;

    public static Fragment_List newInstance() {
        Log.d("new fragment list", "newInstance");

        Fragment_List fragment = new Fragment_List();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("on create", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView==null) {
            rootView = inflater.inflate(R.layout.fragment_list, container, false);
        }
        RecyclerView recyclerView = rootView.findViewById(R.id.fragment_RCV_list);

        highScore = new High_Score();
        players = highScore.readScores();

        //Create recycle view for player in top
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        if (players.size() <= highScore.MAX_SIZE) {
            adapter = new Recycler_Adapter(players);
        }
        if (players.size() >= highScore.MAX_SIZE) {
            adapter = new Recycler_Adapter(new ArrayList<Player_Info>(players.subList(0, 10)));
        }

        //Show location according to recycle view
        adapter.setOnItemClickListener(new Recycler_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Player_Info gameUser = players.get(position);
                listener.onSentPlayerInfo(gameUser);
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Fragment_High_Score_List) {
            listener = (Fragment_High_Score_List) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

}
