package ru.geekbrains.android2.repository

interface Repository<T> {
    suspend fun getData(word: String): T
}