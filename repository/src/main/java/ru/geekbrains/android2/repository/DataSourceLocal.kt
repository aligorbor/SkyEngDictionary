package ru.geekbrains.android2.repository

import ru.geekbrains.android2.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
    suspend fun getAllData(): T
}