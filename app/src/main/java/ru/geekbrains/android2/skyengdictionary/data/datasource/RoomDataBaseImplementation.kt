package ru.geekbrains.android2.skyengdictionary.data.datasource

import ru.geekbrains.android2.skyengdictionary.data.AppState
import ru.geekbrains.android2.skyengdictionary.data.room.HistoryDao
import ru.geekbrains.android2.skyengdictionary.data.word.Word
import ru.geekbrains.android2.skyengdictionary.utils.convertDataModelSuccessToEntities
import ru.geekbrains.android2.skyengdictionary.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) : DataSourceLocal<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return mapHistoryEntityToSearchResult(historyDao.getDataByWord(word))
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntities(appState)?.let {
            historyDao.insertAll(it)
        }
    }

    override suspend fun getAllData(): List<Word> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }
}