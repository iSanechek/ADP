package ru.isanechek.repository.di

import org.koin.dsl.module.applicationContext
import ru.isanechek.common.tools.Mapper
import ru.isanechek.common.vo.Episode
import ru.isanechek.network.vo.RssItem
import ru.isanechek.repository.Repository
import ru.isanechek.repository.RepositoryImpl
import ru.isanechek.repository.tools.EpisodeItemToEpisodeMapper
import ru.isanechek.repository.tools.RssToEpisodeItemMapper
import ru.isanechek.storage.database.entity.EpisodeItem

val repositoryModule = applicationContext {
    bean {
        RepositoryImpl(get(), get(), get(),
                get(name = "RssToEpisodeItem"), get(name = "EpItemToEp")) as Repository
    }

    bean(name = "RssToEpisodeItem") {
        RssToEpisodeItemMapper() as Mapper<RssItem, EpisodeItem>
    }

    bean(name = "EpItemToEp") {
        EpisodeItemToEpisodeMapper(get()) as Mapper<EpisodeItem, Episode>
    }
}