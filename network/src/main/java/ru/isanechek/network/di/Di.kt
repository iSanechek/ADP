package ru.isanechek.network.di

import org.koin.dsl.module.applicationContext
import ru.isanechek.network.RssManager
import ru.isanechek.network.RssManagerImpl
import ru.isanechek.network.parser.RssParser
import ru.isanechek.network.parser.RssParserImpl

val networkModule = applicationContext {
    bean {
        RssManagerImpl(get()) as RssManager
    }

    bean {
        RssParserImpl(get()) as RssParser
    }
}