package ru.geekbrains.android2.skyengdictionary.data.datasource

import ru.geekbrains.android2.skyengdictionary.data.AppState

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
    suspend fun getAllData(): T
}