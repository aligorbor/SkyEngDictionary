package ru.geekbrains.android2.historyscreen.view.history

import ru.geekbrains.android2.core.viewmodel.Interactor
import ru.geekbrains.android2.model.data.AppState
import ru.geekbrains.android2.model.data.Word
import ru.geekbrains.android2.repository.Repository
import ru.geekbrains.android2.repository.RepositoryLocal

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
