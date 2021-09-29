package ru.geekbrains.android2.skyengdictionary.data.datasource

interface DataSource<T> {

    suspend fun getData(word: String): T
}
