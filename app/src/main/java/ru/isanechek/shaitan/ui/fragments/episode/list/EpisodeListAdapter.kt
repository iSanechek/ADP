package ru.isanechek.shaitan.ui.fragments.episode.list

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import ru.isanechek.common.helpers.DateHelper
import ru.isanechek.common.vo.Episode
import ru.isanechek.shaitan.utils.extensions.inflate
import ru.isanechek.shaitan.utils.tools._id
import ru.isanechek.shaitan.utils.tools._layout

class EpisodeListAdapter : PagedListAdapter<Episode, EpisodeListAdapter.EpisodeHolder>(diffCallback) {

    interface OnItemClickListener {
        fun onItemClick(view: View, episode: Episode, position: Int)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Episode>() {
            override fun areItemsTheSame(oldItem: Episode?, newItem: Episode?): Boolean =
                    oldItem == newItem

            override fun areContentsTheSame(oldItem: Episode?, newItem: Episode?): Boolean =
                    oldItem?.id == newItem?.id
        }
    }

    private var callbackClickListener: OnItemClickListener? = null

    fun setOnClickListener(callbackClickListener: OnItemClickListener?) {
        this.callbackClickListener = callbackClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeHolder =
            EpisodeHolder(parent)

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {
        holder.bindTo(getItem(position), callbackClickListener, position)
    }

    class EpisodeHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(
            parent?.inflate(_layout.episode_list_item_layout)) {
        private val container: LinearLayout = itemView.findViewById(_id.episode_list_item_container)
        private val titleTv: TextView = itemView.findViewById(_id.episode_list_item_title)
        private val descriptionTv: TextView = itemView.findViewById(_id.episode_list_item_description)
        private val dateTv: TextView = itemView.findViewById(_id.episode_list_item_date_tv)
        private val starBtn: ImageButton = itemView.findViewById(_id.episode_list_item_star_btn)

        fun bindTo(episode: Episode?,
                   callback: OnItemClickListener?,
                   position: Int) {
            titleTv.text = episode?.title
            dateTv.text = episode?.pubData
            descriptionTv.text = episode?.description
            container.setOnClickListener {
                callback?.onItemClick(it, episode!!, position)
            }
        }
    }
}