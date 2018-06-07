package ru.isanechek.shaitan.ui.fragments.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.isanechek.shaitan.ui.main.MainActivity

abstract class BaseScreen : Fragment() {

    protected lateinit var activity: MainActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MainActivity) {
            this.activity = context
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutResId(), container, false)

    protected abstract fun layoutResId(): Int
}