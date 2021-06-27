package com.bagasn.inoutcomeapp.db

import android.content.Context

class AppDatabase(context: Context) {

    companion object {
        private const val DATABASE_NAME = "account_history.sqlite"
        private const val DATABASE_VERSION = 1
    }

    private val sqlHelper: SQLHelper

    init {
        sqlHelper = SQLHelper(context, DATABASE_NAME, DATABASE_VERSION)
    }

}