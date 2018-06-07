package ru.isanechek.storage.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = EpisodeItem.TABLE_NAME)
data class EpisodeItem(
        @PrimaryKey @ColumnInfo(name = EpisodeItem.COLUMN_NAME) val id: Int,
        val link: String,
        val title: String,
        val author: String,
        @ColumnInfo(name = EpisodeItem.COLUMN_DATA) val pubData: Long,
        val description: String,
        val explicit: String,
        val duration: String,
        val mp3Link: String,
        val mp3Length: Long,
        val mp3Type: String,
        val content: String) {
    companion object {
        const val TABLE_NAME = "episode_name"
        const val COLUMN_NAME = "id"
        const val COLUMN_DATA = "pub_data"
    }
}