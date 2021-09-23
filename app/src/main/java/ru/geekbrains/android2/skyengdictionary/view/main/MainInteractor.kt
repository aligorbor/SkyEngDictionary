package ru.geekbrains.android2.skyengdictionary.view.main

import io.reactivex.Observable
import ru.geekbrains.android2.skyengdictionary.data.AppState
import ru.geekbrains.android2.skyengdictionary.data.repository.Repository
import ru.geekbrains.android2.skyengdictionary.data.word.Word
import ru.geekbrains.android2.skyengdictionary.di.NAME_LOCAL
import ru.geekbrains.android2.skyengdictionary.di.NAME_REMOTE
import ru.geekbrains.android2.skyengdictionary.viewmodel.Interactor
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) private val remoteRepository: Repository<List<Word>>,
    @Named(NAME_LOCAL) private val localRepository: Repository<List<Word>>
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map {
                AppState.Success(it)
            }
        } else {
            localRepository.getData(word).map {
                AppState.Success(it)
            }
        }
    }


}