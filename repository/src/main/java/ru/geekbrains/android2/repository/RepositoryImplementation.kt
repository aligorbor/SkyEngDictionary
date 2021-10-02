package ru.geekbrains.android2.repository

import ru.geekbrains.android2.model.data.Word

class RepositoryImplementation(private val dataSource: DataSource<List<Word>>) :
    Repository<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }
}
