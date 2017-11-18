package com.example.teffe.troopersapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.teffe.troopersapp.model.Trooper;
import com.example.teffe.troopersapp.util.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements TrooperAdapter.OnItemClickListener,
        View.OnLongClickListener {

    private RecyclerView rvTroopers;
    private ArrayList<Trooper> troopers;
    private TrooperAdapter trooperAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getString(R.string.troopers));

        rvTroopers = findViewById(R.id.rv_troopers);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTroopers.setLayoutManager(layoutManager);

        SharedPreferences sharedPreferences =
                getSharedPreferences(Constants.TROOPER_PREFS, MODE_PRIVATE);

        troopers = TrooperRepository
                .tryGettingFromSharedPreferences(sharedPreferences);

        trooperAdapter = new TrooperAdapter(troopers,
                this,
                this);

        rvTroopers.setAdapter(trooperAdapter);
    }

    @Override
    public void onItemClick(Trooper trooper) {
        Intent intent = new Intent(this, TrooperDetailActivity.class);
        intent.putExtra(Constants.TROOPER_EXTRA, trooper);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.confirmation_remove_trooper)
                .setTitle(R.string.remove_trooper_title)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int position = rvTroopers.getChildLayoutPosition(view);
                        troopers.remove(position);
                        trooperAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this,
                                R.string.trooper_removed,
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();

        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences =
                getSharedPreferences(Constants.TROOPER_PREFS, MODE_PRIVATE);

        TrooperRepository.saveToSharedPreferences(sharedPreferences, troopers);
    }
}
