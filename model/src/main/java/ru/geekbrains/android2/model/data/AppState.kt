package ru.geekbrains.android2.model.data


sealed class AppState {
    data class Success(val data: List<Word>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
