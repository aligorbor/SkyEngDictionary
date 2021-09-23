package ru.geekbrains.android2.skyengdictionary.scheduler

import io.reactivex.Scheduler

interface ISchedulerProvider {
    fun ui(): Scheduler

    fun io(): Scheduler
}