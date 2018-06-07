package ru.isanechek.network.vo

data class RssItem(val title: String,
                   val link: String,
                   val author: String,
                   val pubData: Long,
                   val description: String,
                   val explicit: String,
                   val duration: String,
                   val mp3Link: String,
                   val mp3Length: Long,
                   val mp3Type: String,
                   val content: String)