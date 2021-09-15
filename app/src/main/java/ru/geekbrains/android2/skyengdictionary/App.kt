package ru.geekbrains.android2.skyengdictionary

import android.app.Application
import ru.geekbrains.android2.skyengdictionary.data.BaseReposData
import ru.geekbrains.android2.skyengdictionary.data.BaseReposDataFactory
import ru.geekbrains.android2.skyengdictionary.presenter.BasePresenter
import ru.geekbrains.android2.skyengdictionary.presenter.MainPresenter
import ru.geekbrains.android2.skyengdictionary.scheduler.SchedulersFactory

class App : Application() {
    lateinit var dataRepos: BaseReposData
    lateinit var mainPresenter: BasePresenter

    override fun onCreate() {
        super.onCreate()
        instance = this
        dataRepos = BaseReposDataFactory.create()
        mainPresenter = MainPresenter(dataRepos, SchedulersFactory.create())
    }

    companion object {
        lateinit var instance: App
            private set
    }
}