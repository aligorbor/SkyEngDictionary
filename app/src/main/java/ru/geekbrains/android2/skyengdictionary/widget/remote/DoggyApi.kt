package ru.geekbrains.android2.skyengdictionary.widget.remote

import retrofit2.http.GET

interface DoggyApi {
    @GET("random")
    suspend fun getRandomDog():DoggyResponse
}