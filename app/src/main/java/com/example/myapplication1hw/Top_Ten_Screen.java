package com.example.myapplication1hw;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.os.Bundle;
import android.widget.ImageView;

public class Top_Ten_Screen extends Activity_Base implements Fragment_High_Score_List{
    private ImageView top10_IMG_back;
    private Fragment_Map fragmentMap;
    private Fragment_List fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten_screen);
        initFragments();
        findViews();
        addClickListeners();
    }

    private void initFragments() {
        //Initialize fragment
        fragmentMap = new Fragment_Map();
        fragmentList = new Fragment_List();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.top10_LAY_list, fragmentList);
        fragmentTransaction.add(R.id.top10_LAY_map, fragmentMap);
        fragmentTransaction.commit();
    }

    private void addClickListeners() {
        top10_IMG_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMenu();
            }
        });
    }

    private void findViews() {
        top10_IMG_back =findViewById(R.id.top10_IMG_back);
    }

    private void backToMenu() {
        finish();
    }

    @Override
    public void onSentPlayerInfo(Player_Info player) {
        fragmentMap.getPlayerLocation(player);
    }
}


