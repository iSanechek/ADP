package ru.isanechek.network.vo

data class ResponseRss(val channel: Channel?,
                       val items: List<RssItem>,
                       val message: String) {
    companion object {
        const val NEED_UPDATE = "need.update"
        const val NO_NEED_UPDATE = "no.need.update"
        const val ERROR = "error.msg"
    }
}