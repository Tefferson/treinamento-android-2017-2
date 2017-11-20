package com.example.teffe.troopersapp.util

import android.content.SharedPreferences

/**
 * Created by teffe on 20/11/2017.
 */
class SharedPreferencesUtil(private val sharedPreferences: SharedPreferences) {

    fun get(key: String): String = sharedPreferences.getString(key, "")
    fun hasValue(key: String): Boolean = sharedPreferences.contains(key)
    fun save(key: String, value: String) =
            sharedPreferences
                    .edit()
                    .putString(key, value)
                    .apply()
}