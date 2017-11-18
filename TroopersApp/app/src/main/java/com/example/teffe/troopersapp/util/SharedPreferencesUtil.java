package com.example.teffe.troopersapp.util;

import android.content.SharedPreferences;

/**
 * Created by teffe on 18/11/2017.
 */

public class SharedPreferencesUtil {
    private SharedPreferences sharedPreferences;

    public SharedPreferencesUtil(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public String get(String key) {
        return sharedPreferences.getString(key, "");
    }

    public boolean hasValue(String key) {
        return sharedPreferences.contains(key);
    }

    public void save(String key, String value) {
        sharedPreferences
                .edit()
                .putString(key, value)
                .apply();
    }
}
