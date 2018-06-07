package ru.isanechek.network.vo

data class Channel(
        val title: String,
        val link: String,
        val description: String,
        val lastBuildData: Long,
        val generator: String,
        val cover: String,
        val email: String,
        val category: String)