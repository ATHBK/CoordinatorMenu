package com.example.athbk.coordinatormenu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.athbk.coordinatormenu.R;

/**
 * Created by ATHBK on 10,September,2018
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ItemVH> {

    @NonNull
    @Override
    public ItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ItemVH extends RecyclerView.ViewHolder {

        public ItemVH(View itemView) {
            super(itemView);
        }
    }
}
