package ru.geekbrains.android2.model.data.DTO

import com.google.gson.annotations.SerializedName

data class MeaningDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("partOfSpeechCode") val partOfSpeechCode: String?,
    @SerializedName("translation") val translation: TranslationDTO?,
    @SerializedName("imageUrl") val imageUrl: String?
)
