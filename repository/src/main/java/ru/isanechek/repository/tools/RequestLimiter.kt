package ru.isanechek.repository.tools

import android.os.SystemClock
import ru.isanechek.storage.pref.PrefManager
import java.util.concurrent.TimeUnit

class RequestLimiter<KEY>(timeout: Int, timeUnit: TimeUnit, private val pref: PrefManager) {
    private val timeout: Long = timeUnit.toMillis(timeout.toLong())

    @Synchronized
    fun shouldFetch(key: KEY): Boolean {
        val lastFetched = pref.getLimiterTime(key as String)
        val now = now()
        if (lastFetched == 0L) {
            pref.setLimiterTime(key as String, now)
            return true
        }
        if (now - lastFetched > timeout) {
            pref.setLimiterTime(key as String, now)
            return true
        }
        return false
    }

    private fun now(): Long = SystemClock.uptimeMillis()

    @Synchronized
    fun reset(key: KEY) {
    }

    companion object {
        const val RSS_KEY = "rss.key.limitr"
    }
}