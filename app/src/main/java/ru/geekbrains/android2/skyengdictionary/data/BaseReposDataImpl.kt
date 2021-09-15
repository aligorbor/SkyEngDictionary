package ru.geekbrains.android2.skyengdictionary.data

import io.reactivex.Single
import ru.geekbrains.android2.skyengdictionary.data.api.SkyEngApi
import ru.geekbrains.android2.skyengdictionary.data.word.Word

class BaseReposDataImpl(private val skyEngApi: SkyEngApi) : BaseReposData {
    override fun returnTranslation(searchWord: String): Single<List<Word>> =
        skyEngApi.getTranslationByWord(searchWord)
}