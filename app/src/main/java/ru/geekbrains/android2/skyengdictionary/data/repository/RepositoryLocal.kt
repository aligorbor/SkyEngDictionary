package ru.geekbrains.android2.skyengdictionary.data.repository

import ru.geekbrains.android2.skyengdictionary.data.AppState

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
    suspend fun getAllData(): T
}