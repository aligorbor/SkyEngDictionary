package ru.geekbrains.android2.skyengdictionary.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.geekbrains.android2.skyengdictionary.data.word.Word

interface SkyEngApi {
    @GET("/api/public/v1/words/search")
    fun getTranslationByWord(@Query("search") searchWord: String): Single<List<Word>>
}