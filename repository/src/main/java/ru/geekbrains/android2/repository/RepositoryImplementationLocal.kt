package ru.geekbrains.android2.repository

import ru.geekbrains.android2.model.data.AppState
import ru.geekbrains.android2.model.data.Word

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<Word>>) :
    RepositoryLocal<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getAllData(): List<Word> {
        return dataSource.getAllData()
    }
}
