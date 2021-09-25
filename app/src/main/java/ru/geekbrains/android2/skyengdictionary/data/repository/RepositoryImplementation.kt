package ru.geekbrains.android2.skyengdictionary.data.repository

import ru.geekbrains.android2.skyengdictionary.data.datasource.DataSource
import ru.geekbrains.android2.skyengdictionary.data.word.Word

class RepositoryImplementation(private val dataSource: DataSource<List<Word>>) :
    Repository<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }
}
