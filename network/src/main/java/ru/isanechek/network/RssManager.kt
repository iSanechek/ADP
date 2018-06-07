package ru.isanechek.network

import com.github.kittinunf.fuel.Fuel
import ru.isanechek.network.parser.RssParser
import ru.isanechek.network.vo.ResponseRss

interface RssManager {
    fun getDataFromRss(link: String, data: Long): ResponseRss
}

class RssManagerImpl(
        private val rssParser: RssParser
) : RssManager {

    override fun getDataFromRss(link: String, data: Long): ResponseRss {
        val response = Fuel.get(link).responseString()
        return rssParser.rssMapper(response.third.component1(), data)
    }
}