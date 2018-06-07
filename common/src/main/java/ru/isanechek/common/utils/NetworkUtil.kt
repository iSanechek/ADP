package ru.isanechek.common.utils

interface NetworkChecker {
    fun checkConnection(): Boolean
}

class NetworkUtil : NetworkChecker {
    override fun checkConnection(): Boolean {
        return true
    }

}