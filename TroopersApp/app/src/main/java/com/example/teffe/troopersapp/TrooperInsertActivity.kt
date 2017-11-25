package com.example.teffe.troopersapp

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.teffe.troopersapp.database.DbController
import com.example.teffe.troopersapp.model.Affiliation
import com.example.teffe.troopersapp.model.Trooper
import com.example.teffe.troopersapp.model.TrooperPager
import com.example.teffe.troopersapp.pager.TrooperPagerAdapter

class TrooperInsertActivity : AppCompatActivity() {

    private var viewPager: ViewPager? = null
    private var btNext: Button? = null
    private val db = DbController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trooper_insert)

        viewPager = findViewById(R.id.view_pager)
        val adapter = TrooperPagerAdapter(this, viewPager!!)
        viewPager?.adapter = adapter

        btNext = findViewById(R.id.bt_next)
        btNext?.setOnClickListener({
            if (viewPager!!.currentItem < TrooperPager.values().size - 1) {
                viewPager?.currentItem = viewPager!!.currentItem + 1
            } else {
                saveTrooper(adapter)
                Toast.makeText(this, "Trooper saved.", Toast.LENGTH_SHORT).show()
                finish()
            }
        })

        viewPager?.offscreenPageLimit = Int.MAX_VALUE
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int,
                                        positionOffset: Float,
                                        positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (position < TrooperPager.values().size - 1) {
                    btNext?.text = getString(R.string.next)
                } else {
                    btNext?.text = getString(R.string.create)
                }
            }
        })
    }

    private fun saveTrooper(adapter: TrooperPagerAdapter) {
        val idxAffiliation = adapter.lvAffiliation!!.checkedItemPosition
        val trooper = Trooper(
                0,
                adapter.etName!!.text.toString(),
                adapter.etImageUrl!!.text.toString(),
                adapter.etDescription!!.text.toString(),
                Affiliation.values().get(idxAffiliation)
        )

        db.insert(trooper)
    }

    override fun onBackPressed() {
        if (viewPager!!.currentItem > 0) {
            viewPager?.currentItem = viewPager!!.currentItem - 1
        } else {
            super.onBackPressed()
        }
    }
}
