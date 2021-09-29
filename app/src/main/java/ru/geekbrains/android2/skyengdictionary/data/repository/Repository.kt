package ru.geekbrains.android2.skyengdictionary.data.repository

interface Repository<T> {
    suspend fun getData(word: String): T
}