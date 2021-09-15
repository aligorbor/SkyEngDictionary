package ru.geekbrains.android2.skyengdictionary.scheduler

object SchedulersFactory {
    fun create(): Schedulers = DefaultSchedulers()
}