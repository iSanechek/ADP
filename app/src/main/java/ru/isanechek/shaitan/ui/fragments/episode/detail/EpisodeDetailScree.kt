package ru.isanechek.shaitan.ui.fragments.episode.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import ru.isanechek.common.vo.Episode
import ru.isanechek.shaitan.ui.fragments.base.BaseScreen
import ru.isanechek.shaitan.utils.tools._id
import ru.isanechek.shaitan.utils.tools._layout

class EpisodeDetailScree : BaseScreen() {

    // view's
    private lateinit var cover: ImageView
    private lateinit var title: TextView
    private lateinit var seek: SeekBar
    private lateinit var startTimeTv: TextView
    private lateinit var endTimeTv: TextView
    private lateinit var favBtn: ImageButton
    private lateinit var playPauseBtn: ImageButton
    private lateinit var shareBtn: ImageButton
    private lateinit var replayBtn: ImageButton
    private lateinit var forwardBtn: ImageButton
    private lateinit var infoBtn: ImageButton
    private lateinit var settingBtn: ImageButton

    private var episode: Episode? = null
        get() = arguments?.getParcelable("episode")

    override fun layoutResId(): Int =
            _layout.episode_detail_screen_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cover = view.findViewById(_id.episode_detail_screen_cover)
        title = view.findViewById(_id.episode_detail_screen_title)
        seek = view.findViewById(_id.episode_detail_screen_seekBar)
        startTimeTv = view.findViewById(_id.episode_detail_screen_start_time_tv)
        endTimeTv = view.findViewById(_id.episode_detail_screen_end_time_tv)
        favBtn = view.findViewById(_id.episode_detail_screen_fav_btn)
        playPauseBtn = view.findViewById(_id.episode_detail_screen_play_pause_btn)
        shareBtn = view.findViewById(_id.episode_detail_screen_share_btn)
        replayBtn = view.findViewById(_id.episode_detail_screen_replay_btn)
        forwardBtn = view.findViewById(_id.episode_detail_screen_forward_btn)
        infoBtn = view.findViewById(_id.episode_detail_screen_info_btn)
        settingBtn = view.findViewById(_id.episode_detail_screen_setting_btn)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        episode?.let {
            title.text = it.title
        }
    }

}