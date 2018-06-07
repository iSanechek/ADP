package ru.isanechek.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.annotation.MainThread
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.withContext
import ru.isanechek.common.tools.Mapper
import ru.isanechek.common.vo.Episode
import ru.isanechek.network.RssManager
import ru.isanechek.network.RssManagerImpl
import ru.isanechek.network.vo.ResponseRss
import ru.isanechek.network.vo.RssItem
import ru.isanechek.repository.tools.RequestLimiter
import ru.isanechek.storage.database.dao.PodcastDao
import ru.isanechek.storage.database.entity.EpisodeItem
import ru.isanechek.storage.pref.PrefManager
import java.util.concurrent.TimeUnit

interface Repository {
    fun status(): LiveData<ActionStatus>
    suspend fun loadRss()
    suspend fun updateRss()
    fun loadEpisodes(): LiveData<PagedList<Episode>>
}

class RepositoryImpl(
        private val podcastDao: PodcastDao,
        private val pref: PrefManager,
        private val rssManager: RssManager,
        private val mapper: Mapper<RssItem, EpisodeItem>,
        private val epMapper: Mapper<EpisodeItem, Episode>
) : Repository {
    private val requestLimiter = RequestLimiter<String>(1, TimeUnit.DAYS, pref)
    private val actionStatus = MutableLiveData<ActionStatus>()

    override fun status(): LiveData<ActionStatus> = actionStatus

    override suspend fun loadRss() = withContext(newFixedThreadPoolContext(3, "io")) {
        if (requestLimiter.shouldFetch(RequestLimiter.RSS_KEY)) {
            actionStatus.postValue(ActionStatus.LOADING)
            val response = rssManager.getDataFromRss(RSS_FEED, pref.dateUpdate)
            when (response.message) {
                ResponseRss.NEED_UPDATE -> {
                    podcastDao.insert(mapper.map(response.items))
                    pref.dateUpdate = response.channel?.lastBuildData?:0L
                    actionStatus.postValue(ActionStatus.LOADED)
                }
                ResponseRss.NO_NEED_UPDATE -> actionStatus.postValue(ActionStatus.LOADED)
                ResponseRss.ERROR -> actionStatus.postValue(ActionStatus.error(response.message))
            }
        }
    }

    override suspend fun updateRss() {
        requestLimiter.reset(RequestLimiter.RSS_KEY)
        loadRss()
    }

    @MainThread
    override fun loadEpisodes(): LiveData<PagedList<Episode>> =
            LivePagedListBuilder(podcastDao.load().map { item ->
                epMapper.map(item)
    }, 10).build()

    companion object {
        private const val RSS_FEED = "https://apptractor.ru/Podcast/android.xml"
    }

}

