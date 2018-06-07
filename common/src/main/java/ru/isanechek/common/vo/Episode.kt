package ru.isanechek.common.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Episode(val id: Int,
                   val link: String,
                   val title: String,
                   val author: String,
                   val pubData: String,
                   val description: String,
                   val explicit: String,
                   val duration: String,
                   val mp3Link: String,
                   val mp3Length: Long,
                   val mp3Type: String,
                   val content: String) : Parcelable