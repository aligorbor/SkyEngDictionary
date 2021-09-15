package ru.geekbrains.android2.skyengdictionary.data.word

import com.google.gson.annotations.SerializedName

data class Word(
    @SerializedName("id") val id: Int,
    @SerializedName("text") val text: String,
    @SerializedName("meanings") val meanings: List<Meaning>
)
