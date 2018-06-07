package ru.isanechek.common.di

import org.koin.dsl.module.applicationContext
import ru.isanechek.common.helpers.DateHelper
import ru.isanechek.common.helpers.DateHelperImpl
import ru.isanechek.common.utils.NetworkChecker
import ru.isanechek.common.utils.NetworkUtil

val commonModule = applicationContext {

    bean {
        DateHelperImpl() as DateHelper
    }

    bean {
        NetworkUtil() as NetworkChecker
    }
}