package com.bagasn.inoutcomeapp.db.model

data class AccountHistoryModel(
    val id: Int? = null,
    val description: String,
    val value: Int,
    val category: String,
    val datetime: String,
)