package ru.isanechek.storage.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.isanechek.storage.database.dao.PodcastDao
import ru.isanechek.storage.database.entity.EpisodeItem

@Database(entities = [EpisodeItem::class], version = 2)
abstract class ShaitanDb : RoomDatabase() {
    abstract fun podcastDao(): PodcastDao
}