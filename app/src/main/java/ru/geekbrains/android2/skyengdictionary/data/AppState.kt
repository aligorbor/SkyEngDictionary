package ru.geekbrains.android2.skyengdictionary.data

import ru.geekbrains.android2.skyengdictionary.data.word.Word

sealed class AppState {
    data class Success(val data: List<Word>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
