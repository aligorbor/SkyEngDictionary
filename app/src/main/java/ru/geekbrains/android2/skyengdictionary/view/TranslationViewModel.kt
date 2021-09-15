package ru.geekbrains.android2.skyengdictionary.view

import ru.geekbrains.android2.skyengdictionary.data.word.Meaning

data class TranslationViewModel(
    val id: Int = 0,
    val text: String = ""
) {
    object Mapper {
        fun map(meaning: Meaning) =
            TranslationViewModel(
                meaning.id,
                meaning.translation.text
            )
    }
}
