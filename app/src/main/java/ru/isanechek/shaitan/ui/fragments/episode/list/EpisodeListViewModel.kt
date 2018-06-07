package ru.isanechek.shaitan.ui.fragments.episode.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import kotlinx.coroutines.experimental.android.UI
import ru.isanechek.common.vo.Episode
import ru.isanechek.repository.ActionStatus
import ru.isanechek.repository.Repository
import ru.isanechek.shaitan.utils.helper.launchAction

class EpisodeListViewModel(
        private val repository:Repository
) : ViewModel() {

    val status: LiveData<ActionStatus> = repository.status()

    val data: LiveData<PagedList<Episode>> = repository.loadEpisodes()

    fun loadData() = launchAction(UI) {
        repository.loadRss()
    }

    fun refresh() = launchAction(UI) {
        repository.loadRss()
    }
}