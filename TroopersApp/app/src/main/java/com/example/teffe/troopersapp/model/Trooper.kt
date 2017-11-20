package com.example.teffe.troopersapp.model

import java.io.Serializable

/**
 * Created by teffe on 19/11/2017.
 */

class Trooper(val id: Int,
              val name: String,
              val imageUrl: String,
              val description: String,
              val affiliation: Affiliation)
    : Serializable