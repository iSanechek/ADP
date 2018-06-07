package ru.isanechek.storage.database.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import ru.isanechek.storage.database.entity.EpisodeItem

@Dao
interface PodcastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(episodeItem: EpisodeItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(episodeItems: List<EpisodeItem>)

    @Query("SELECT * FROM ${EpisodeItem.TABLE_NAME} ORDER BY ${EpisodeItem.COLUMN_DATA} DESC")
    fun load(): DataSource.Factory<Int, EpisodeItem>

    @Query("SELECT * FROM ${EpisodeItem.TABLE_NAME} WHERE ${EpisodeItem.COLUMN_NAME} =:id LIMIT 1")
    fun load(id: Int): EpisodeItem
}