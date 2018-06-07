package ru.isanechek.network.parser

import org.w3c.dom.Element
import org.xml.sax.InputSource
import ru.isanechek.common.helpers.DateHelper
import ru.isanechek.network.vo.Channel
import ru.isanechek.network.vo.ResponseRss
import ru.isanechek.network.vo.RssItem
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Created by isanechek on 1/1/18.
 */

interface RssParser {
    fun rssMapper(input: String?, oldDate: Long): ResponseRss
}

class RssParserImpl(
        private val dateHelper: DateHelper
) : RssParser {

    override fun rssMapper(input: String?, oldDate: Long): ResponseRss {
        if (input == null) return ResponseRss(null, emptyList(), ResponseRss.ERROR)
        val e = element(input)
        val date = getTagContent("lastBuildDate", e)
        val now = dateHelper.getDateLongFromString(date)
        if (now == oldDate) return ResponseRss(null, emptyList(), ResponseRss.NO_NEED_UPDATE)

        val channel = Channel(
                title = getTagContent("title", e),
                link = getTagContent("link", e),
                description = getTagContent("description", e),
                lastBuildData = now,
                generator = getTagContent("generator", e),
                cover = getTagContent("url", e),
                email = getTagContent("itunes:email", e),
                category = getTagWithAttrContent("itunes:category", "text", e)
        )

        val items = e.getElementsByTagName("item")
        val cache = mutableListOf<RssItem>()
        for (i in 0 until items.length) {
            val item = items.item(i) as Element
            cache.add(RssItem(
                    title = getTagContent("title", item),
                    link = getTagContent("link", item),
                    author = getTagContent("author", item),
                    pubData = dateHelper.getDateLongFromString(getTagContent("pubDate", item).replace(">", "")),
                    description = getTagContent("description", item),
                    explicit = getTagContent("itunes:explicit", item),
                    duration = getTagContent("itunes:duration", item),
                    mp3Link = getTagWithAttrContent("enclosure", "url", item),
                    mp3Length = getTagWithAttrContent("enclosure", "length", item).toLong(),
                    mp3Type = getTagWithAttrContent("enclosure", "type", item),
                    content = item.getElementsByTagName("content:encoded").item(0)?.textContent ?: "empty"))
        }
        return ResponseRss(channel, cache, ResponseRss.NEED_UPDATE)
    }

    private fun getTagContent(tag: String, channel: Element) : String = channel
            .getElementsByTagName(tag)
            .item(0)
            .firstChild
            .nodeValue ?: ""

    private fun getTagWithAttrContent(tag: String, attribute: String, channel: Element) : String {
        val node = channel.getElementsByTagName(tag).item(0) as Element
        return node.getAttribute(attribute)
    }

    private fun element(input: String) : Element {
        val dbf = DocumentBuilderFactory.newInstance()
        val db = dbf.newDocumentBuilder()
        val doc = db.parse(InputSource(StringReader(input))).documentElement
        return doc.getElementsByTagName("channel").item(0) as Element
    }
}