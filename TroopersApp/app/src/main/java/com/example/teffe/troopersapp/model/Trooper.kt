package com.example.teffe.troopersapp.model

import java.io.Serializable

/**
 * Created by teffe on 19/11/2017.
 */

class Trooper(var id: Int,
              var name: String,
              var imageUrl: String,
              var description: String,
              var affiliation: Affiliation)
    : Serializable {
    var isFavorite: Boolean = false
}