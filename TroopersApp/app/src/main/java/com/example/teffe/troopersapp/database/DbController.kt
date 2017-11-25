package com.example.teffe.troopersapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.teffe.troopersapp.model.Affiliation
import com.example.teffe.troopersapp.model.Trooper
import java.util.logging.Logger

/**
 * Created by teffe on 24/11/2017.
 */
class DbController(context: Context) {

    private val dbCreator = DbCreator(context)

    fun insert(trooper: Trooper): Boolean {
        val db = dbCreator.writableDatabase
        val values = ContentValues()
        values.run {
            put(DbCreator.tNAME, trooper.name)
            put(DbCreator.tDESCRIPTION, trooper.description)
            put(DbCreator.tIMAGE_URL, trooper.imageUrl)
            put(DbCreator.tAFFILIATION, trooper.affiliation.ordinal)
            put(DbCreator.tIS_FAVORITE, trooper.isFavorite)
        }
        val result = db.insert(DbCreator.tTABLE, null, values)
        db.close()

        return result > -1
    }

    fun load(): ArrayList<Trooper> {
        val troopers = ArrayList<Trooper>()

        val db = dbCreator.readableDatabase
        val sql = "SELECT * FROM ${DbCreator.tTABLE} ORDER BY ${DbCreator.tID} DESC"
        val cursor = db.query(DbCreator.tTABLE,
                null,
                null,
                null,
                null,
                null,
                DbCreator.tID)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(DbCreator.tID))
                val name = cursor.getString(cursor.getColumnIndex(DbCreator.tNAME))
                val description = cursor.getString(cursor.getColumnIndex(DbCreator.tDESCRIPTION))
                val imageUrl = cursor.getString(cursor.getColumnIndex(DbCreator.tIMAGE_URL))
                val idxAffiliation = cursor.getInt(cursor.getColumnIndex(DbCreator.tAFFILIATION))
                val affiliation = Affiliation.values().get(idxAffiliation)
                val isFavorite = cursor.getInt(cursor.getColumnIndex(DbCreator.tIS_FAVORITE)) == 1

                val trooper = Trooper(id, name, imageUrl, description, affiliation)
                trooper.isFavorite = isFavorite
                troopers.add(trooper)
            } while (cursor.moveToNext())
        }

        db.close()

        return troopers
    }

    fun updateIsFavorite(id: Int, isFavorite: Boolean) {
        val db = dbCreator.writableDatabase
        val sql = "UPDATE ${DbCreator.tTABLE} " +
                "SET ${DbCreator.tIS_FAVORITE} = ${if (isFavorite) 1 else 0} " +
                "WHERE ${DbCreator.tID} = $id"
        db.execSQL(sql)
        db.close()
    }

    private class DbCreator(context: Context) : SQLiteOpenHelper(context, "trooper.dbo", null, 1) {

        companion object {
            val tTABLE = "TROOPER"
            val tID = "id"
            val tNAME = "name"
            val tDESCRIPTION = "description"
            val tIMAGE_URL = "imageUrl"
            val tAFFILIATION = "affiliation"
            val tIS_FAVORITE = "isFavorite"
        }

        override fun onCreate(db: SQLiteDatabase?) {

            val sql = "CREATE TABLE $tTABLE(" +
                    "$tID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$tNAME TEXT," +
                    "$tDESCRIPTION TEXT," +
                    "$tIMAGE_URL TEXT," +
                    "$tAFFILIATION TEXT," +
                    "$tIS_FAVORITE INTEGER" +
                    ")"

            db!!.execSQL(sql)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS $tTABLE")
            onCreate(db)
        }
    }

    fun remove(id: Int) {
        val db = dbCreator.writableDatabase
        val sql = "DELETE FROM ${DbCreator.tTABLE} " +
                "WHERE ${DbCreator.tID} = $id"
        db.execSQL(sql)
        db.close()
    }
}