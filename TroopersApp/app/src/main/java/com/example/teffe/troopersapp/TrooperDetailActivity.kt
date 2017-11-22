package com.example.teffe.troopersapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.teffe.troopersapp.model.Trooper
import com.example.teffe.troopersapp.util.Constants
import com.example.teffe.troopersapp.util.ResourceUtil
import com.squareup.picasso.Picasso


/**
 * Created by teffe on 18/11/2017.
 */
class TrooperDetailActivity : AppCompatActivity() {

    private var tvTrooperDescription: TextView? = null
    private var ivTrooper: ImageView? = null
    private var ivAffiliation: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trooper_detail)

        tvTrooperDescription = findViewById<TextView>(R.id.tv_description)
        ivTrooper = findViewById(R.id.iv_trooper)
        ivAffiliation = findViewById(R.id.iv_affiliation)

        var trooper = intent.getSerializableExtra(Constants.TROOPER_EXTRA) as Trooper

        title = trooper.name

        tvTrooperDescription?.text = trooper.description

        ivAffiliation?.setImageResource(ResourceUtil
                .getResourceBasedOnAffiliation(trooper.affiliation))

        Picasso.with(this)
                .load(trooper.imageUrl)
                .into(ivTrooper)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_trooper_detail, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.item_star -> {
                Toast.makeText(this, R.string.like_trooper, Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}