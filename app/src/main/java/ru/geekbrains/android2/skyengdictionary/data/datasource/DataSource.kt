package ru.geekbrains.android2.skyengdictionary.data.datasource

import io.reactivex.Observable

interface DataSource<T> {

    fun getData(word: String): Observable<T>
}
