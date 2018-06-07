package ru.isanechek.storage.pref

import android.content.SharedPreferences
import androidx.core.content.edit

interface PrefManager {
    var dateUpdate: Long
    fun getLimiterTime(key: String): Long
    fun setLimiterTime(key: String, value: Long)
}

class PrefManagerImpl(
        private val sharedPreferences: SharedPreferences
): PrefManager {

    override fun getLimiterTime(key: String): Long = sharedPreferences.getLong(key, 0L)

    override fun setLimiterTime(key: String, value: Long) {
        sharedPreferences.edit {
            putLong(key, value)
        }
    }

    override var dateUpdate: Long
        get() = sharedPreferences.getLong(LAST_DATA_ITEM, 0L)
        set(value) {
            sharedPreferences.edit {
                putLong(LAST_DATA_ITEM, value)
            }
        }

    companion object {
        private const val LAST_DATA_ITEM = "last.data.item"
        private const val REQUEST_LIMITER_KAY = "request.limitr.key"
    }
}