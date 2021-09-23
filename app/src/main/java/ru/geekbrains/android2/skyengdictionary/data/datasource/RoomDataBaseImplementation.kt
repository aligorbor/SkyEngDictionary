package ru.geekbrains.android2.skyengdictionary.data.datasource

import io.reactivex.Observable
import ru.geekbrains.android2.skyengdictionary.data.word.Word

class RoomDataBaseImplementation : DataSource<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> {
        TODO("not implemented")
    }
}