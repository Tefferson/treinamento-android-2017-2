package com.example.teffe.starwars;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.teffe.starwars.RecyclerView.LineAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ArrayList<ListViewItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);

        populateList();

        setUpRecyclerView();

        recyclerView.setOnClickListener(this);
    }

    private void populateList() {
        list.add(new ListViewItem(R.drawable.man_user, getString(R.string.people)));
        list.add(new ListViewItem(R.drawable.starship, getString(R.string.starship)));
        list.add(new ListViewItem(R.drawable.film, getString(R.string.film)));
        list.add(new ListViewItem(R.drawable.earth_globe, getString(R.string.planets)));
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        LineAdapter adapter = new LineAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        //Intent intent = new Intent(this, R);
    }
}
