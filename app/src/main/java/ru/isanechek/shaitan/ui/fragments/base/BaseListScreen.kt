package ru.isanechek.shaitan.ui.fragments.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import ru.isanechek.shaitan.ui.widgets.Dp
import ru.isanechek.shaitan.ui.widgets.WaterfallToolbar
import ru.isanechek.shaitan.utils.tools._id
import ru.isanechek.shaitan.utils.tools._layout

open class BaseListScreen : BaseScreen() {

    lateinit var refresh: SwipeRefreshLayout
    lateinit var timeline: RecyclerView
    lateinit var toolbar: Toolbar

    override fun layoutResId(): Int =
            _layout.base_list_screen_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh = view.findViewById(_id.base_list_screen_srl)
        timeline = view.findViewById(_id.base_list_screen_rv)
        toolbar = view.findViewById(_id.base_list_screen_toolbar)
        view.findViewById<WaterfallToolbar>(_id.base_list_screen_waterfall)
                .apply {
                    recyclerView = timeline
                }
    }

    fun navigateTo(@IdRes id: Int) {

    }
}