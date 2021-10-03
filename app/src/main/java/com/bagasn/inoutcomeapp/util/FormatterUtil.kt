package com.bagasn.inoutcomeapp.util

import android.util.Log
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object FormatterUtil {
    private const val TAG = "FormatterUtil"

    const val PATTERN_OUTPUT_DATE = "dd MMM yyyy"
    const val PATTERN_DATE_TIME = "dd/MM/yyyy'T'HH:mm:ss"

    val SHORT_MONTHS = arrayOf(
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "Mei",
        "Jun",
        "Jul",
        "Agu",
        "Sep",
        "Okt",
        "Nov",
        "Des"
    )

    val FULL_MONTHS = arrayOf(
        "Januari",
        "Februari",
        "Maret",
        "April",
        "Mei",
        "Juni",
        "Juli",
        "Agustus",
        "September",
        "Oktober",
        "November",
        "Desember"
    )

    fun stringToCalendar(input: String, pattern: String): Calendar? {
        return try {
            val date = SimpleDateFormat(pattern, Locale.US)
                .parse(input)

            val cal = Calendar.getInstance()
            cal.time = date
            cal
        } catch (e: ParseException) {
            printLog(e)
            null
        }
    }

    fun dateToString(calendar: Calendar, pattern: String, locale: Locale = Locale.US): String? {
        return try {
            val formatter = SimpleDateFormat(pattern, locale)
            formatter.format(calendar.time)
        } catch (e: ParseException) {
            null
        }
    }

    fun doubleToSeparateNumber(value: Double? = 0.0): String {
        val formatter = DecimalFormat("#,###", DecimalFormatSymbols(Locale.GERMAN))
        return formatter.format(value)
    }

    fun longToSeparateNumber(value: Long? = 0L): String {
        val formatter = DecimalFormat("#,###", DecimalFormatSymbols(Locale.GERMAN))
        return formatter.format(value)
    }

    fun stringToLong(textCurrency: String): Long {
        if (textCurrency.isEmpty()) return 0L
        val value = textCurrency.replace("[^\\d]".toRegex(), "")
        return if (value.isEmpty()) 0L else value.toLong()
    }

    private fun printLog(e: Exception) {
        Log.e(TAG, "printLog: ${e.message}", e)
    }

}