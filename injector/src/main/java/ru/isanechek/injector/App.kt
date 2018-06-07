package ru.isanechek.injector

import android.app.Application
import org.koin.android.ext.android.startKoin
import ru.isanechek.injector.di.allModules

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin(this, allModules)
    }
}