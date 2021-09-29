package ru.geekbrains.android2.skyengdictionary.utils

import ru.geekbrains.android2.skyengdictionary.data.AppState
import ru.geekbrains.android2.skyengdictionary.data.room.HistoryEntity
import ru.geekbrains.android2.skyengdictionary.data.word.Meaning
import ru.geekbrains.android2.skyengdictionary.data.word.Translation
import ru.geekbrains.android2.skyengdictionary.data.word.Word

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
            newWords.add(Word(word.id, word.text, newMeanings))
        }
    }
}


fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<Word> {
    val searchResult = ArrayList<Word>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(
                Word(
                    0,
                    entity.word,
                    arrayListOf(
                        Meaning(
                            0, "",
                            Translation(entity.description ?: "", ""),
                            entity.imageUrl ?: ""
                        )
                    )
                )
            )
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty()
                || searchResult[0].text.isNullOrEmpty()
                || searchResult[0].meanings.isNullOrEmpty()
            ) {
                null
            } else {
                HistoryEntity(
                    searchResult[0].text!!,
                    convertMeaningsToString(searchResult[0].meanings),
                    searchResult[0].meanings?.get(0)?.imageUrl
                )
            }
        }
        else -> null
    }
}

fun convertDataModelSuccessToEntities(appState: AppState): List<HistoryEntity>? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty()
                || searchResult[0].text.isNullOrEmpty()
                || searchResult[0].meanings.isNullOrEmpty()
            ) {
                null
            } else {
                val entities = ArrayList<HistoryEntity>()
                for (word in searchResult) {
                    entities.add(
                        HistoryEntity(
                            word.text!!,
                            convertMeaningsToString(word.meanings),
                            word.meanings?.get(0)?.imageUrl
                        )
                    )
                }
                entities
            }
        }
        else -> null
    }
}

fun convertMeaningsToString(meanings: List<Meaning>?): String {
    var meaningsSeparatedByComma = String()
    meanings?.let {
        for ((index, meaning) in meanings.withIndex()) {
            meaningsSeparatedByComma += if (index + 1 != meanings.size) {
                String.format("%s%s", meaning.translation?.text, ", ")
            } else {
                meaning.translation?.text
            }
        }
    }
    return meaningsSeparatedByComma
}