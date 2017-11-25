package com.example.teffe.troopersapp.model

import com.example.teffe.troopersapp.R

/**
 * Created by teffe on 22/11/2017.
 */
enum class TrooperPager(val titleResId: Int, val layoutResId: Int) {
    AFFILIATION(R.string.affiliation, R.layout.view_trooper_affiliation),
    NAME(R.string.name, R.layout.view_trooper_name),
    DESCRIPTION(R.string.description, R.layout.view_trooper_description),
    IMAGE(R.string.image_url, R.layout.view_trooper_image_url)
}