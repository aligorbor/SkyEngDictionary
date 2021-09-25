package ru.geekbrains.android2.skyengdictionary.view.main

import ru.geekbrains.android2.skyengdictionary.data.AppState
import ru.geekbrains.android2.skyengdictionary.data.repository.Repository
import ru.geekbrains.android2.skyengdictionary.data.word.Word
import ru.geekbrains.android2.skyengdictionary.viewmodel.Interactor

class MainInteractor(
    private val remoteRepository: Repository<List<Word>>,
    private val localRepository: Repository<List<Word>>
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                remoteRepository
            } else {
                localRepository
            }.getData(word)
        )
    }


}