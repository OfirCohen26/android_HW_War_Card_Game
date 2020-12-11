package com.example.myapplication1hw;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.MyViewHolder> {

    private ArrayList<Player_Info> player_List;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public Recycler_Adapter(ArrayList<Player_Info> list) {
        this.player_List = list;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView player_LBL_name;
        private TextView player_LBL_score;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            player_LBL_name = itemView.findViewById(R.id.player_LBL_name);
            player_LBL_score = itemView.findViewById(R.id.player_LBL_score);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public Recycler_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_item, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view, mListener);
        return myViewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_Adapter.MyViewHolder holder, int position) {
        Player_Info currentItem = player_List.get(position);
        holder.player_LBL_name.setText(currentItem.getName());
        holder.player_LBL_score.setText("Score: " + currentItem.getScore());
    }

    @Override
    public int getItemCount() {
        return player_List.size();
    }
}
