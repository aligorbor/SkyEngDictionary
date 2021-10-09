package ru.geekbrains.android2.model.data

data class Meaning(
    val id: Int = 0,
    val partOfSpeechCode: String = "",
    val translation: Translation = Translation(),
    val imageUrl: String = ""
)
