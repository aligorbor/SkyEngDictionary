package ru.geekbrains.android2.repository

interface DataSource<T> {

    suspend fun getData(word: String): T
}
