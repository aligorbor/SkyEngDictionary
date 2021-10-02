package ru.geekbrains.android2.repository

import ru.geekbrains.android2.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
    suspend fun getAllData(): T
}