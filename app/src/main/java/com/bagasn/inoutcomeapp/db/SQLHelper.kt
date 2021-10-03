package com.bagasn.inoutcomeapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.bagasn.inoutcomeapp.db.table.TableAccountHistory
import com.bagasn.inoutcomeapp.db.table.TableCategoryAccount

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
        db?.execSQL(TableAccountHistory.queryCreateTable)
        db?.execSQL(TableCategoryAccount.queryCreateTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.let { database ->
            database.execSQL("DROP TABLE IF EXISTS ${TableAccountHistory.FeedAccount.TABLE_NAME}")
            database.execSQL("DROP TABLE IF EXISTS ${TableCategoryAccount.FeedCategory.TABLE_NAME}")
        }

        onCreate(db)
    }
}