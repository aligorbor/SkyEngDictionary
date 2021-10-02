package ru.geekbrains.android2.model.data

import com.google.gson.annotations.SerializedName

data class Meaning(
    @SerializedName("id") val id: Int,
    @SerializedName("partOfSpeechCode") val partOfSpeechCode: String,
    @SerializedName("translation") val translation: Translation,
    @SerializedName("imageUrl") val imageUrl: String
)
