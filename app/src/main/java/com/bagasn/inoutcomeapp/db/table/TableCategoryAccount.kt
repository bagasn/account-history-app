package com.bagasn.inoutcomeapp.db.table

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.provider.BaseColumns
import android.util.Log
import com.bagasn.inoutcomeapp.db.model.CategoryAccountModel

object TableCategoryAccount : TableContext<CategoryAccountModel> {

    object FeedCategory : BaseColumns {
        const val TABLE_NAME = "account_category"
        const val COLUMN_DESCRIPTION = "description"
    }

    override val queryCreateTable: String
        get() = "CREATE TABLE IF NOT EXISTS ${FeedCategory.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                "${FeedCategory.COLUMN_DESCRIPTION} TEXT UNIQUE" +
                ")"

    override val columns: Array<String>
        get() = arrayOf(
            BaseColumns._ID,
            FeedCategory.COLUMN_DESCRIPTION
        )

    override fun add(db: SQLiteDatabase, model: CategoryAccountModel): Long {
        try {
            return db.insert(
                FeedCategory.TABLE_NAME,
                null,
                toContentValue(model)
            )
        } catch (e: SQLiteException) {
            Log.e(TAG, "insert", e)
        }
        return -1L
    }

    override fun get(
        db: SQLiteDatabase,
        selection: String?,
        selectionArgs: Array<String>?
    ): List<CategoryAccountModel> {
        val list = mutableListOf<CategoryAccountModel>()

        try {
            val rows = db.query(
                FeedCategory.TABLE_NAME,
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

    override fun getById(db: SQLiteDatabase, id: Int): CategoryAccountModel? {
         try {
            val rows = get(db, "${BaseColumns._ID} = $id", null)
            if (rows.isNotEmpty()) return rows[0]
        } catch (e: SQLiteException) {
            Log.e(TAG, "getById", e)
        }

        return null
    }

    override fun update(db: SQLiteDatabase, id: Int, model: CategoryAccountModel): Long {
        TODO("Not yet implemented")
    }

    override fun delete(db: SQLiteDatabase, id: Int): Long {
        TODO("Not yet implemented")
    }

    override fun delete(db: SQLiteDatabase, where: String): Long {
        TODO("Not yet implemented")
    }

    override fun toContentValue(model: CategoryAccountModel): ContentValues {
        return ContentValues().apply {
            put(FeedCategory.COLUMN_DESCRIPTION, model.description)
        }
    }

    override fun toModel(row: Cursor): CategoryAccountModel {
        return CategoryAccountModel(
            row.getInt(row.getColumnIndex(BaseColumns._ID)),
            row.getString(row.getColumnIndex(FeedCategory.COLUMN_DESCRIPTION)),
        )
    }
}