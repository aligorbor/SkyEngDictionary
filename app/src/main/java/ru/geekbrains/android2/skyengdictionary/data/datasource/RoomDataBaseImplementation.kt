package ru.geekbrains.android2.skyengdictionary.data.datasource

import ru.geekbrains.android2.skyengdictionary.data.word.Word

class RoomDataBaseImplementation : DataSource<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        TODO("not implemented")
    }
}