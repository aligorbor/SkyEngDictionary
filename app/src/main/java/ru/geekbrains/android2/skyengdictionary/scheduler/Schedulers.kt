package ru.geekbrains.android2.skyengdictionary.scheduler

import io.reactivex.Scheduler

interface Schedulers {
    fun background(): Scheduler
    fun main(): Scheduler
}