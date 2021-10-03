package com.bagasn.inoutcomeapp.db

import android.content.Context

class AppDatabase(context: Context) {

    companion object {
        private const val DATABASE_NAME = "account_history.sqlite"
        private const val DATABASE_VERSION = 4
    }

    private val sqlHelper: SQLHelper = SQLHelper(context, DATABASE_NAME, DATABASE_VERSION)

    fun getDatabase(writeable: Boolean = false) = if (writeable) {
        sqlHelper.writableDatabase
    } else {
        sqlHelper.readableDatabase
    }

}