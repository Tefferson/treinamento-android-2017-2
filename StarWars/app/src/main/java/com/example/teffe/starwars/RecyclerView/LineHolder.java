package com.example.teffe.starwars.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teffe.starwars.R;

/**
 * Created by teffe on 18/11/2017.
 */

public class LineHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textView;

    public LineHolder(View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.tv_name);
        textView.setText("Nome atribuído");

        imageView = itemView.findViewById(R.id.iv_icon);
    }
}