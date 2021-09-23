package ru.geekbrains.android2.skyengdictionary.data.repository

import io.reactivex.Observable

interface Repository<T> {
    fun getData(word: String): Observable<T>
}