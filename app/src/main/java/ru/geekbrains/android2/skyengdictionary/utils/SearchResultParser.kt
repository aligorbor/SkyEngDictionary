package ru.geekbrains.android2.skyengdictionary.utils

import ru.geekbrains.android2.model.data.AppState
import ru.geekbrains.android2.model.data.DTO.WordDTO
import ru.geekbrains.android2.model.data.Meaning
import ru.geekbrains.android2.model.data.Translation
import ru.geekbrains.android2.model.data.Word

fun mapSearchResultToResult(searchResults: List<WordDTO>): List<Word> {
    return searchResults.map { searchResult ->
        var meanings: List<Meaning> = listOf()
        searchResult.meanings?.let {
            meanings = it.map { meaningDto ->
                Meaning(
                    meaningDto.id ?: 0,
                    meaningDto.partOfSpeechCode ?: "",
                    Translation(
                        meaningDto.translation?.text ?: "",
                        meaningDto.translation?.note ?: ""
                    ),
                    meaningDto.imageUrl ?: ""
                )
            }
        }
        Word(searchResult.id ?: 0, searchResult.text ?: "", meanings)
    }
}


fun parseSearchResults(data: AppState): AppState {
    val newSearchResults = arrayListOf<Word>()
    when (data) {
        is AppState.Success -> {
            val searchResults = data.data
            if (!searchResults.isNullOrEmpty()) {
                for (searchResult in searchResults) {
                    parseResults(searchResult, newSearchResults)
                }
            }
        }
    }

    return AppState.Success(newSearchResults)
}

private fun parseResults(word: Word, newDataModels: ArrayList<Word>) {
    if (!word.text.isBlank() && !word.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meaning>()
        for (meaning in word.meanings) {
            newMeanings.add(
                Meaning(
                    meaning.id,
                    meaning.partOfSpeechCode,
                    meaning.translation,
                    meaning.imageUrl
                )
            )
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(Word(word.id, word.text, newMeanings))
        }
    }
}

fun parseSearchResult(appState: AppState): AppState {
    return AppState.Success(mapResult(appState))
}

private fun mapResult(appState: AppState): List<Word> {
    val newSearchResults = arrayListOf<Word>()
    when (appState) {
        is AppState.Success -> {
            getSuccessResultData(appState, newSearchResults)
        }
    }
    return newSearchResults
}

private fun getSuccessResultData(
    appState: AppState.Success,
    newWords: ArrayList<Word>
) {
    val words: List<Word> = appState.data as List<Word>
    if (words.isNotEmpty())
        for (searchResult in words) parseResult(searchResult, newWords)
}

private fun parseResult(word: Word, newWords: ArrayList<Word>) {
    if (!word.text.isBlank() && !word.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meaning>()
        for (meaning in word.meanings) {
            newMeanings.add(
                Meaning(
                    meaning.id,
                    meaning.partOfSpeechCode,
                    meaning.translation,
                    meaning.imageUrl
                )
            )
        }
        if (newMeanings.isNotEmpty()) {
            newWords.add(Word(word.id, word.text, newMeanings))
        }
    }
}


fun convertMeaningsToString(meanings: List<Meaning>?): String {
    var meaningsSeparatedByComma = String()
    meanings?.let {
        for ((index, meaning) in meanings.withIndex()) {
            meaningsSeparatedByComma += if (index + 1 != meanings.size) {
                String.format("%s%s", meaning.translation.text, ", ")
            } else {
                meaning.translation.text
            }
        }
    }
    return meaningsSeparatedByComma
}