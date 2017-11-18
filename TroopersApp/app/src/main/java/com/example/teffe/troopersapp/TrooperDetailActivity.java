package com.example.teffe.troopersapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teffe.troopersapp.model.Trooper;
import com.example.teffe.troopersapp.util.Constants;
import com.example.teffe.troopersapp.util.ResourceUtil;
import com.squareup.picasso.Picasso;

import java.util.logging.Logger;

/**
 * Created by teffe on 18/11/2017.
 */

public class TrooperDetailActivity extends AppCompatActivity {

    private TextView tvTrooperDescription;
    private ImageView ivTrooper;
    private ImageView ivAffiliation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trooper_detail);

        tvTrooperDescription = findViewById(R.id.tv_description);
        ivTrooper = findViewById(R.id.iv_trooper);
        ivAffiliation = findViewById(R.id.iv_affiliation);

        Intent intent = getIntent();

        Trooper trooper = (Trooper) intent.getExtras().get(Constants.TROOPER_EXTRA);

        setTitle(trooper.getName());

        tvTrooperDescription.setText(trooper.getDescription());

        ivAffiliation.setImageResource(ResourceUtil
                .getResourceBasedOnAffiliation(trooper.getAffiliation()));

        Picasso.with(this)
                .load(trooper.getImageUrl())
                .into(ivTrooper);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.trooper_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.start_item:
                Toast.makeText(this, R.string.like_trooper, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}