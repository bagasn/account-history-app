package com.bagasn.inoutcomeapp.db.table

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.provider.BaseColumns
import android.util.Log
import com.bagasn.inoutcomeapp.db.model.AccountHistoryModel

object TableAccountHistory : TableContext<AccountHistoryModel> {

    object FeedAccount : BaseColumns {
        const val TABLE_NAME = "account_history"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_VALUE = "value"
        const val COLUMN_CATEGORY = "category"
        const val COLUMN_DATETIME = "datetime"
    }

    override val queryCreateTable: String
        get() = "CREATE TABLE IF NOT EXISTS ${FeedAccount.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                "${FeedAccount.COLUMN_DESCRIPTION} TEXT, " +
                "${FeedAccount.COLUMN_VALUE} INTEGER, " +
                "${FeedAccount.COLUMN_CATEGORY} INTEGER, " +
                "${FeedAccount.COLUMN_DATETIME} TEXT" +
                ")"

    override val columns: Array<String> = arrayOf(
        BaseColumns._ID,
        FeedAccount.COLUMN_DESCRIPTION,
        FeedAccount.COLUMN_VALUE,
        FeedAccount.COLUMN_CATEGORY,
        FeedAccount.COLUMN_DATETIME
    )

    override fun add(db: SQLiteDatabase, model: AccountHistoryModel): Long {
        try {
            return db.insert(
                FeedAccount.TABLE_NAME,
                null,
                toContentValue(model)
            )
        } catch (e: Exception) {
            Log.e(TAG, "insert", e)
        }
        return -1L
    }

    override fun get(
        db: SQLiteDatabase,
        selection: String?,
        selectionArgs: Array<String>?
    ): List<AccountHistoryModel> {
        val list = mutableListOf<AccountHistoryModel>()

        try {
            val rows = db.query(
                FeedAccount.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
            )

            while (rows.moveToNext()) {
                list.add(toModel(rows))
            }

            rows.close()
        } catch (e: SQLiteException) {
            Log.e(TAG, "get", e)
        }

        return list
    }

    override fun getById(db: SQLiteDatabase, id: Int): AccountHistoryModel? {
        TODO("Not yet implemented")
    }

    override fun update(db: SQLiteDatabase, id: Int, model: AccountHistoryModel): Long {
        TODO("Not yet implemented")
    }

    override fun delete(db: SQLiteDatabase, id: Int): Long {
        TODO("Not yet implemented")
    }

    override fun delete(db: SQLiteDatabase, where: String): Long {
        TODO("Not yet implemented")
    }

    override fun toContentValue(model: AccountHistoryModel): ContentValues {
        return ContentValues().apply {
            put(FeedAccount.COLUMN_DESCRIPTION, model.description)
            put(FeedAccount.COLUMN_VALUE, model.value)
            put(FeedAccount.COLUMN_CATEGORY, model.category)
            put(FeedAccount.COLUMN_DATETIME, model.datetime)
        }
    }

    override fun toModel(row: Cursor): AccountHistoryModel {
        return AccountHistoryModel(
            id = row.getInt(row.getColumnIndex(BaseColumns._ID)),
            description = row.getString(row.getColumnIndex(FeedAccount.COLUMN_DESCRIPTION)),
            value = row.getInt(row.getColumnIndex(FeedAccount.COLUMN_VALUE)),
            category = row.getString(row.getColumnIndex(FeedAccount.COLUMN_CATEGORY)),
            datetime = row.getString(row.getColumnIndex(FeedAccount.COLUMN_DATETIME))
        )
    }

}