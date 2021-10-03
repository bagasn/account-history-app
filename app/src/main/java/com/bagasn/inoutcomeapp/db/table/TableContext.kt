package com.bagasn.inoutcomeapp.db.table

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

interface TableContext<MODEL> {

    val TAG: String
        get() = javaClass.simpleName

    val queryCreateTable: String

    val columns: Array<String>

    fun add(db: SQLiteDatabase, model: MODEL): Long

    fun get(
        db: SQLiteDatabase,
        selection: String? = null,
        selectionArgs: Array<String>? = null,
    ): List<MODEL>

    fun getById(db: SQLiteDatabase, id: Int): MODEL?

    fun update(db: SQLiteDatabase, id: Int, model: MODEL): Long

    fun delete(db: SQLiteDatabase, id: Int): Long

    fun delete(db: SQLiteDatabase, where: String): Long

    fun toContentValue(model: MODEL): ContentValues

    fun toModel(row: Cursor): MODEL

}