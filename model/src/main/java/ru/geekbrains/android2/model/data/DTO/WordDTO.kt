package ru.geekbrains.android2.model.data.DTO

import com.google.gson.annotations.SerializedName

data class WordDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("text") val text: String?,
    @SerializedName("meanings") val meanings: List<MeaningDTO>?
)
