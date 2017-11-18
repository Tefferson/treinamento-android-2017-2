package com.example.teffe.starwars.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.teffe.starwars.ListViewItem;
import com.example.teffe.starwars.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teffe on 18/11/2017.
 */

public class LineAdapter extends RecyclerView.Adapter<LineHolder> {

    private List<ListViewItem> items;

    public LineAdapter(ArrayList items) {
        this.items = items;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(LineHolder holder, int position) {
        ListViewItem item = items.get(position);
        holder.textView.setText(item.description);
        holder.imageView.setImageResource(item.icon);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}