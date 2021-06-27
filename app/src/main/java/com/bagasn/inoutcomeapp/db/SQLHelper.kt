package com.bagasn.inoutcomeapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLHelper(
    context: Context,
    name: String,
    version: Int
) : SQLiteOpenHelper(
    context,
    name,
    null,
    version
) {
    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        onCreate(db)
    }
}