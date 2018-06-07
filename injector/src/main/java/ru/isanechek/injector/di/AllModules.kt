package ru.isanechek.injector.di

import ru.isanechek.common.di.commonModule
import ru.isanechek.network.di.networkModule
import ru.isanechek.repository.di.repositoryModule
import ru.isanechek.shaitan.di.appModule
import ru.isanechek.storage.di.storageModule

val allModules = listOf(
        appModule,
        networkModule,
        commonModule,
        storageModule,
        repositoryModule)