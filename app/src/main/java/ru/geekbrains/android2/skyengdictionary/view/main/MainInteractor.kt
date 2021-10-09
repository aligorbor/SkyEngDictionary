package ru.geekbrains.android2.skyengdictionary.view.main

import ru.geekbrains.android2.core.viewmodel.Interactor
import ru.geekbrains.android2.model.data.AppState
import ru.geekbrains.android2.model.data.DTO.WordDTO
import ru.geekbrains.android2.repository.Repository
import ru.geekbrains.android2.repository.RepositoryLocal
import ru.geekbrains.android2.skyengdictionary.utils.mapSearchResultToResult

class MainInteractor(
    private val repositoryRemote: Repository<List<WordDTO>>,
    private val repositoryLocal: RepositoryLocal<List<WordDTO>>
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(mapSearchResultToResult(repositoryRemote.getData(word)))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
        }
        return appState
    }
}