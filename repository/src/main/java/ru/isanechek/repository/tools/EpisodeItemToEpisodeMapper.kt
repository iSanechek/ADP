package ru.isanechek.repository.tools

import ru.isanechek.common.helpers.DateHelper
import ru.isanechek.common.tools.Mapper
import ru.isanechek.common.vo.Episode
import ru.isanechek.storage.database.entity.EpisodeItem

class EpisodeItemToEpisodeMapper(
        private val dateHelper: DateHelper
) : Mapper<EpisodeItem, Episode> {
    override fun map(from: EpisodeItem): Episode = Episode(
            id = from.id,
            link = from.link,
            title = from.title,
            author = from.author,
            pubData = dateHelper.getDateStringFromLong(from.pubData),
            description = from.description,
            explicit = from.explicit,
            duration = from.duration,
            mp3Link = from.mp3Link,
            mp3Length = from.mp3Length,
            mp3Type = from.mp3Type,
            content = from.content)

    override fun map(from: List<EpisodeItem>): List<Episode> {
        return emptyList()
    }

}