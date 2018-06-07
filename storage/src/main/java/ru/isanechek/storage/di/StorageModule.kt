package ru.isanechek.storage.di

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext
import ru.isanechek.storage.database.ShaitanDb
import ru.isanechek.storage.pref.PrefManager
import ru.isanechek.storage.pref.PrefManagerImpl

val storageModule = applicationContext {
    bean {
        androidApplication()
                .applicationContext
                .getSharedPreferences("shaitan", Context.MODE_PRIVATE)
    } bind (SharedPreferences::class)

    bean {
        PrefManagerImpl(get()) as PrefManager
    }

    bean {
        Room.databaseBuilder(androidApplication(), ShaitanDb::class.java, "shaitan.db")
                .fallbackToDestructiveMigration()
                .build()
    }
    bean {
        get<ShaitanDb>().podcastDao()
    }
}