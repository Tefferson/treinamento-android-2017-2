package com.example.teffe.troopersapp.util

import com.example.teffe.troopersapp.R
import com.example.teffe.troopersapp.model.Affiliation

/**
 * Created by teffe on 20/11/2017.
 */
object ResourceUtil {
    fun getResourceBasedOnAffiliation(affiliation: Affiliation?): Int {
        return when (affiliation) {
            Affiliation.GALACTIC_EMPIRE -> R.drawable.galactic_empire
            Affiliation.GALACTIC_REPUBLIC -> R.drawable.galactic_republic
            Affiliation.FIRST_ORDER -> R.drawable.first_order
            else -> 0
        }
    }

    fun getNameBasedOnAffiliation(affiliation: Affiliation?): Int {
        return when (affiliation) {
            Affiliation.FIRST_ORDER -> R.string.first_order
            Affiliation.GALACTIC_EMPIRE -> R.string.galactic_empire
            Affiliation.GALACTIC_REPUBLIC -> R.string.galactic_republic
            else -> 0
        }
    }
}