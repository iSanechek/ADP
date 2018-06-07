package ru.isanechek.repository.tools

import ru.isanechek.common.tools.Mapper
import ru.isanechek.network.vo.RssItem
import ru.isanechek.storage.database.entity.EpisodeItem

class RssToEpisodeItemMapper : Mapper<RssItem, EpisodeItem> {

    override fun map(from: RssItem): EpisodeItem = EpisodeItem(
            id = from.title.hashCode(),
            link = from.link,
            title = from.title,
            author = from.author,
            pubData = from.pubData,
            description = from.description,
            explicit = from.explicit,
            duration = from.duration,
            mp3Link = from.mp3Link,
            mp3Length = from.mp3Length,
            mp3Type = from.mp3Type,
            content = from.content)

    override fun map(from: List<RssItem>): List<EpisodeItem> = from
            .map { map(it) }
            .toList()

}