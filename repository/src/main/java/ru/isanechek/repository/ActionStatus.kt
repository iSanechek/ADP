package ru.isanechek.repository

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class ActionStatus private constructor(
        val status: Status,
        val msg: String = "") {
    companion object {
        val LOADED = ActionStatus(Status.SUCCESS)
        val LOADING = ActionStatus(Status.RUNNING)
        fun error(msg: String) = ActionStatus(Status.FAILED, msg)
    }
}