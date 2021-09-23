package ru.geekbrains.android2.skyengdictionary.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.geekbrains.android2.skyengdictionary.data.word.Word

interface ApiService {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<Word>>
}