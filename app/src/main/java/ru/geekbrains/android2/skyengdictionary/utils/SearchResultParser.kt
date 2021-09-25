package ru.geekbrains.android2.skyengdictionary.utils

import ru.geekbrains.android2.skyengdictionary.data.AppState
import ru.geekbrains.android2.skyengdictionary.data.word.Meaning
import ru.geekbrains.android2.skyengdictionary.data.word.Word

fun parseSearchResults(data: AppState): AppState {
    val newSearchResults = arrayListOf<Word>()
    when (data) {
        is AppState.Success -> {
            val searchResults = data.data
            if (!searchResults.isNullOrEmpty()) {
                for (searchResult in searchResults) {
                    parseResult(searchResult, newSearchResults)
                }
            }
        }
    }

    return AppState.Success(newSearchResults)
}

private fun parseResult(word: Word, newDataModels: ArrayList<Word>) {
    if (!word.text.isNullOrBlank() && !word.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meaning>()
        for (meaning in word.meanings) {
            if (meaning.translation != null && !meaning.translation.text.isNullOrBlank()) {
                newMeanings.add(
                    Meaning(
                        meaning.id,
                        meaning.partOfSpeechCode,
                        meaning.translation,
                        meaning.imageUrl
                    )
                )
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(Word(word.id, word.text, newMeanings))
        }
    }
}

fun convertMeaningsToString(meanings: List<Meaning>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.text, ", ")
        } else {
            meaning.translation?.text
        }
    }
    return meaningsSeparatedByComma
}