package com.example.teffe.troopersapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.teffe.troopersapp.model.Trooper
import com.example.teffe.troopersapp.R.string.troopers
import com.example.teffe.troopersapp.util.ResourceUtil


/**
 * Created by teffe on 19/11/2017.
 */
class TrooperAdapter(troopers: ArrayList<Trooper>,
                     onItemClickListener: OnItemClickListener,
                     onLongClickListener: View.OnLongClickListener)
    : RecyclerView.Adapter<TrooperAdapter.ViewHolder>(),
        View.OnClickListener {

    private val troopers = troopers
    private val onItemClickListener = onItemClickListener
    private val onLongClickListener = onLongClickListener

    override fun getItemCount(): Int = troopers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_item_trooper, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trooper = troopers[position]
        holder.tvTrooperName.text = trooper.name
        holder.ivTrooperAffiliation
                .setImageResource(ResourceUtil
                        .getResourceBasedOnAffiliation(trooper.affiliation))

        if (position >= troopers.size - 1) {
            holder.separator.visibility = View.INVISIBLE
        }

        holder.itemView.tag = trooper
        holder.itemView.setOnClickListener(this)
        holder.itemView.setOnLongClickListener(onLongClickListener)
    }

    override fun onClick(view: View) = onItemClickListener.onItemClick(view.tag as Trooper)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTrooperName = itemView.findViewById<TextView>(R.id.tv_description)
        val ivTrooperAffiliation = itemView.findViewById<ImageView>(R.id.iv_icon)
        val separator = itemView.findViewById<View>(R.id.separator)
    }

    interface OnItemClickListener {
        fun onItemClick(trooper: Trooper)
    }
}