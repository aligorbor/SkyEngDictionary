package ru.geekbrains.android2.skyengdictionary.viewmodel

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}