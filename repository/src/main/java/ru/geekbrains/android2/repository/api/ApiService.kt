package ru.geekbrains.android2.repository.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import ru.geekbrains.android2.model.data.DTO.WordDTO

interface ApiService {
    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<WordDTO>>
}