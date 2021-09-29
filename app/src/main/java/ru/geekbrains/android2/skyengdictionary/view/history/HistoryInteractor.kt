package ru.geekbrains.android2.skyengdictionary.view.history

import ru.geekbrains.android2.skyengdictionary.data.AppState
import ru.geekbrains.android2.skyengdictionary.data.repository.Repository
import ru.geekbrains.android2.skyengdictionary.data.repository.RepositoryLocal
import ru.geekbrains.android2.skyengdictionary.data.word.Word
import ru.geekbrains.android2.skyengdictionary.viewmodel.Interactor

class HistoryInteractor(
    private val repositoryRemote: Repository<List<Word>>,
    private val repositoryLocal: RepositoryLocal<List<Word>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }

    suspend fun getAllData(): AppState {
        return AppState.Success(repositoryLocal.getAllData())
    }
}
