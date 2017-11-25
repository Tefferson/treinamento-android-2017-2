package com.example.teffe.troopersapp.pager

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import com.example.teffe.troopersapp.AffiliationAdapter
import com.example.teffe.troopersapp.R
import com.example.teffe.troopersapp.model.Affiliation
import com.example.teffe.troopersapp.model.TrooperPager


/**
 * Created by teffe on 22/11/2017.
 */
class TrooperPagerAdapter(private val context: Context,
                          private val viewPager: ViewPager)
    : PagerAdapter() {

    var lvAffiliation: ListView? = null
    var etName: EditText? = null
    var etDescription: EditText? = null
    var etImageUrl: EditText? = null

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean = view === `object`
    override fun getCount(): Int = TrooperPager.values().size

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val pagerEnum = TrooperPager.values()[position]
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(pagerEnum.layoutResId,
                container,
                false) as ViewGroup
        container!!.addView(layout)

        when (pagerEnum.layoutResId) {
            R.layout.view_trooper_affiliation -> initAffiliationElements(layout)
            R.layout.view_trooper_description -> initDescriptionElements(layout)
            R.layout.view_trooper_image_url -> initImageUrlElements(layout)
            R.layout.view_trooper_name -> initNameElements(layout)
        }

        return layout
    }

    private fun initImageUrlElements(container: ViewGroup) {
        etImageUrl = container.findViewById(R.id.et_trooper_image_url)
    }

    private fun initDescriptionElements(container: ViewGroup) {
        etDescription = container.findViewById(R.id.et_trooper_description)
    }

    private fun initNameElements(container: ViewGroup) {
        etName = container.findViewById(R.id.et_trooper_name)
    }

    private fun initAffiliationElements(container: ViewGroup?) {
        lvAffiliation = container!!.findViewById<ListView>(R.id.lv_affiliation)
        lvAffiliation!!.adapter = AffiliationAdapter(context, Affiliation.values())
        lvAffiliation!!.choiceMode = ListView.CHOICE_MODE_SINGLE
        lvAffiliation!!.setSelector(R.drawable.lv_item_selected)
        lvAffiliation!!.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, _ ->
            viewPager.currentItem = viewPager.currentItem + 1
        }
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container!!.removeView(`object` as View)
    }
}