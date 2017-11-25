package com.example.teffe.troopersapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.teffe.troopersapp.model.Affiliation
import com.example.teffe.troopersapp.util.ResourceUtil

/**
 * Created by teffe on 22/11/2017.
 */
class AffiliationAdapter(context: Context, affiliations: Array<Affiliation>)
    : ArrayAdapter<Affiliation>(context, R.layout.view_item_affiliation, affiliations) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.view_item_affiliation, parent, false)

        val affiliation = getItem(position)

        val imageResource = ResourceUtil.getResourceBasedOnAffiliation(affiliation)
        view.findViewById<ImageView>(R.id.iv_affiliation).setImageResource(imageResource)

        val name = context.getString(ResourceUtil.getNameBasedOnAffiliation(affiliation))
        view.findViewById<TextView>(R.id.tv_description).text = name

        return view
    }
}