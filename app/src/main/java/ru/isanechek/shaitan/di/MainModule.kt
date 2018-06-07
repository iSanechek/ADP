package ru.isanechek.shaitan.di

import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext
import ru.isanechek.shaitan.ui.fragments.episode.detail.EpisodeDetailViewModel
import ru.isanechek.shaitan.ui.fragments.episode.list.EpisodeListViewModel
import ru.isanechek.shaitan.utils.tools.AppExecutors

val appModule = applicationContext {
    viewModel {
        EpisodeListViewModel(get())
    }

    viewModel {
        EpisodeDetailViewModel(get())
    }
}