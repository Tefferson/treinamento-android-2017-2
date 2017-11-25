package com.example.teffe.troopersapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.teffe.troopersapp.database.DbController
import com.example.teffe.troopersapp.model.Trooper
import com.example.teffe.troopersapp.util.Constants
import com.example.teffe.troopersapp.util.ResourceUtil
import com.squareup.picasso.Picasso
import java.util.logging.Logger


/**
 * Created by teffe on 18/11/2017.
 */
class TrooperDetailActivity : AppCompatActivity() {

    private var tvTrooperDescription: TextView? = null
    private var ivTrooper: ImageView? = null
    private var ivAffiliation: ImageView? = null
    private var trooper: Trooper? = null
    private var menu: Menu? = null
    private val db = DbController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trooper_detail)

        tvTrooperDescription = findViewById<TextView>(R.id.tv_description)
        ivTrooper = findViewById(R.id.iv_trooper)
        ivAffiliation = findViewById(R.id.iv_affiliation)

        trooper = intent.getSerializableExtra(Constants.TROOPER_EXTRA) as Trooper

        title = trooper!!.name

        tvTrooperDescription?.text = trooper!!.description

        ivAffiliation?.setImageResource(ResourceUtil
                .getResourceBasedOnAffiliation(trooper!!.affiliation))

        Picasso.with(this)
                .load(trooper!!.imageUrl)
                .into(ivTrooper)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_trooper_detail, menu)
        this.menu = menu
        toggleFavorite()
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.item_star -> {
                trooper!!.isFavorite = true
                saveIsFavorite()
                toggleFavorite()
                Toast.makeText(this, R.string.like_trooper, Toast.LENGTH_SHORT).show()
                true
            }
            R.id.item_star_full -> {
                trooper!!.isFavorite = false
                saveIsFavorite()
                toggleFavorite()
                Toast.makeText(this, R.string.desfazer_favoritar, Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveIsFavorite() {
        db.updateIsFavorite(trooper!!.id, trooper!!.isFavorite)
    }

    private fun toggleFavorite() {
        val isFavorite = trooper!!.isFavorite
        menu!!.findItem(R.id.item_star).isVisible = !isFavorite
        menu!!.findItem(R.id.item_star_full).isVisible = isFavorite
    }
}