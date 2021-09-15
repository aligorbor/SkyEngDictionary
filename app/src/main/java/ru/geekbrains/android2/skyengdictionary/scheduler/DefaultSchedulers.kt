package ru.geekbrains.android2.skyengdictionary.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.newThread

class DefaultSchedulers : Schedulers {

    override fun background(): Scheduler = newThread()

    override fun main(): Scheduler = mainThread()
}