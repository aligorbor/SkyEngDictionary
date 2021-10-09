package ru.geekbrains.android2.historyscreen.view.history

import ru.geekbrains.android2.core.viewmodel.Interactor
import ru.geekbrains.android2.historyscreen.mapSearchResultToResult
import ru.geekbrains.android2.model.data.AppState
import ru.geekbrains.android2.model.data.DTO.WordDTO
import ru.geekbrains.android2.repository.Repository
import ru.geekbrains.android2.repository.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<WordDTO>>,
    private val repositoryLocal: RepositoryLocal<List<WordDTO>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }

    suspend fun getAllData(): AppState {
        return AppState.Success(mapSearchResultToResult(repositoryLocal.getAllData()))
    }
}
