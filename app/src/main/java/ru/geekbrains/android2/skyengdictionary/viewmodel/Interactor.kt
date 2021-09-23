package ru.geekbrains.android2.skyengdictionary.viewmodel

import io.reactivex.Observable

interface Interactor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}