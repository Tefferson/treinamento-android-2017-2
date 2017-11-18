package com.example.teffe.troopersapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teffe.troopersapp.model.Trooper;
import com.example.teffe.troopersapp.util.ResourceUtil;

import java.util.ArrayList;

/**
 * Created by teffe on 18/11/2017.
 */

public class TrooperAdapter extends RecyclerView.Adapter<TrooperAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Trooper> troopers;
    private OnItemClickListener onItemClickListener;
    private View.OnLongClickListener onLongClickListener;

    public TrooperAdapter(ArrayList troopers,
                          OnItemClickListener onItemClickListener,
                          View.OnLongClickListener onLongClickListener) {
        this.troopers = troopers;
        this.onItemClickListener = onItemClickListener;
        this.onLongClickListener = onLongClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_item_trooper, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Trooper trooper = troopers.get(position);
        holder.tvTrooperName.setText(trooper.getName());
        holder.ivTrooperAffiliation
                .setImageResource(ResourceUtil
                        .getResourceBasedOnAffiliation(trooper.getAffiliation()));

        if (position >= troopers.size() - 1) {
            holder.separator.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setTag(trooper);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setOnLongClickListener(onLongClickListener);
    }

    @Override
    public int getItemCount() {
        return troopers.size();
    }

    @Override
    public void onClick(View view) {
        onItemClickListener.onItemClick((Trooper) view.getTag());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTrooperName;
        public ImageView ivTrooperAffiliation;
        public View separator;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTrooperName = itemView.findViewById(R.id.tv_description);
            ivTrooperAffiliation = itemView.findViewById(R.id.iv_icon);
            separator = itemView.findViewById(R.id.separator);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(Trooper trooper);
    }
}
