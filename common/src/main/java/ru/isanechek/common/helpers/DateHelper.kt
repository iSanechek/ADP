package ru.isanechek.common.helpers

import java.text.SimpleDateFormat
import java.util.*

interface DateHelper {
    fun getDateLongFromString(strData: String): Long
    fun getDateStringFromLong(date: Long): String
}

class DateHelperImpl : DateHelper {
    override fun getDateStringFromLong(date: Long): String = SimpleDateFormat("dd MM yy").format(Date(date))

    override fun getDateLongFromString(strData: String): Long = SimpleDateFormat(
                    "EEE, d MMM yyyy HH:mm:ss Z",
                    Locale.US)
                    .parse(strData)
                    .time
}