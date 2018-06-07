package ru.isanechek.shaitan.ui.fragments.episode.list

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import org.koin.android.architecture.ext.getViewModel
import ru.isanechek.common.vo.Episode
import ru.isanechek.repository.ActionStatus
import ru.isanechek.shaitan.ui.fragments.base.BaseListScreen
import ru.isanechek.shaitan.utils.tools._id
import ru.isanechek.shaitan.utils.tools._string

class EpisodeListScreen : BaseListScreen() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar.title = getString(_string.episode_list_screen_title)

        val viewModel = getViewModel<EpisodeListViewModel>()
        viewModel.loadData()
        if (refresh.isRefreshing) refresh.isRefreshing = false
        refresh.setOnRefreshListener {
            viewModel.refresh()
        }

        val episodeAdapter = EpisodeListAdapter()
        episodeAdapter.setOnClickListener(object: EpisodeListAdapter.OnItemClickListener {
            override fun onItemClick(view: View, episode: Episode, position: Int) {
                Navigation.findNavController(view)
                        .navigate(_id.episode_detail_screen, bundleOf("episode" to episode))
            }
        })

        with(timeline) {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = episodeAdapter
        }

        viewModel.data.observe(this, Observer { items ->
            if (items == null) return@Observer
            episodeAdapter.submitList(items)
        })

        viewModel.status.observe(this, Observer { status ->
            if (status == null) return@Observer
            when (status) {
                ActionStatus.LOADING -> if (!refresh.isRefreshing) refresh.isRefreshing = true
                ActionStatus.LOADED -> if (refresh.isRefreshing) refresh.isRefreshing = false
            }
        })

    }
}