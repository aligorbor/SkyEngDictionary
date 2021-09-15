package ru.geekbrains.android2.skyengdictionary.data

import io.reactivex.Single
import ru.geekbrains.android2.skyengdictionary.data.word.Word

interface BaseReposData {
    fun returnTranslation(searchWord: String): Single<List<Word>>
}