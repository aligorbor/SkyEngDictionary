package ru.geekbrains.android2.skyengdictionary

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.geekbrains.android2.skyengdictionary.di.application
import ru.geekbrains.android2.skyengdictionary.di.historyScreen
import ru.geekbrains.android2.skyengdictionary.di.mainScreen

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }

}