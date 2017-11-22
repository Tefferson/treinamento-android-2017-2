package com.example.teffe.troopersapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.teffe.troopersapp.model.Trooper
import com.example.teffe.troopersapp.util.Constants


/**
 * Created by teffe on 19/11/2017.
 */
class MainActivity : AppCompatActivity(),
        TrooperAdapter.OnItemClickListener,
        View.OnLongClickListener {

    private var rvTroopers: RecyclerView? = null
    private var troopers: ArrayList<Trooper>? = null
    private var trooperAdapter: TrooperAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = getString(R.string.troopers)

        rvTroopers = findViewById(R.id.rv_troopers)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        rvTroopers?.layoutManager = LinearLayoutManager(this)

        val sharedPreferences =
                getSharedPreferences(Constants.TROOPER_PREFS, Context.MODE_PRIVATE)

        troopers = TrooperRepository.tryGettingFromSharedPreferences(sharedPreferences)

        trooperAdapter = TrooperAdapter(troopers!!, this, this)

        rvTroopers?.adapter = trooperAdapter
    }

    override fun onLongClick(view: View?): Boolean {

        AlertDialog.Builder(this)
                .setMessage(R.string.confirmation_remove_trooper)
                .setTitle(R.string.remove_trooper_title)
                .setPositiveButton(R.string.yes, { _, _ ->
                    run {
                        var position = rvTroopers!!.getChildLayoutPosition(view)
                        troopers?.removeAt(position)
                        trooperAdapter?.notifyDataSetChanged()
                        Toast.makeText(this,
                                R.string.trooper_removed,
                                Toast.LENGTH_SHORT)
                                .show()
                    }
                })
                .setNegativeButton(R.string.no, { dialogInterface, _ ->
                    run {
                        dialogInterface.dismiss()
                    }
                })
                .create()
                .show()

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_trooper_add, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.item_add -> requestTrooperInfo()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun requestTrooperInfo(): Boolean {
        val intent = Intent(this, TrooperInsertActivity::class.java)
        startActivity(intent)

        return true
    }

    override fun onItemClick(trooper: Trooper) {
        val intent = Intent(this, TrooperDetailActivity::class.java)
        intent.putExtra(Constants.TROOPER_EXTRA, trooper)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        val sharedPreferences = getSharedPreferences(Constants.TROOPER_PREFS, Context.MODE_PRIVATE)
        TrooperRepository.saveToSharedPreferences(sharedPreferences, troopers!!)
    }

}