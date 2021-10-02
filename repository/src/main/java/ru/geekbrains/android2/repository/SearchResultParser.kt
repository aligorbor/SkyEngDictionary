package ru.geekbrains.android2.repository

import ru.geekbrains.android2.model.data.AppState
import ru.geekbrains.android2.model.data.Meaning
import ru.geekbrains.android2.model.data.Translation
import ru.geekbrains.android2.model.data.Word
import ru.geekbrains.android2.repository.room.HistoryEntity


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
                    searchResult[0].text,
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
                            word.text,
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