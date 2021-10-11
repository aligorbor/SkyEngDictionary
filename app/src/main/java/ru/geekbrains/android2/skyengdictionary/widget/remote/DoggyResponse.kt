package ru.geekbrains.android2.skyengdictionary.widget.remote

import com.google.gson.annotations.SerializedName

data class DoggyResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)
