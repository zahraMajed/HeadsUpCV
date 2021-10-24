package com.example.headsupcv

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context?) : SQLiteOpenHelper(context, "data.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("create table celebrates (Name text, Taboo1 text, Taboo2 text, Taboo3 text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun saveData(name:String, t1:String, t2:String, t3:String): Long {
        val ConVal=ContentValues()
        ConVal.put("Name",name)
        ConVal.put("Taboo1",t1)
        ConVal.put("Taboo2",t2)
        ConVal.put("Taboo3",t3)
        val sqliteDB:SQLiteDatabase=writableDatabase
        val status= sqliteDB.insert("celebrates", null,ConVal)
        return status
    }

}