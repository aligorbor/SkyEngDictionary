package ru.geekbrains.android2.historyscreen.view.history

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import ru.geekbrains.android2.core.viewmodel.BaseViewModel
import ru.geekbrains.android2.historyscreen.parseSearchResults
import ru.geekbrains.android2.model.data.AppState

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(parseSearchResults(interactor.getData(word, isOnline)))
    }

    fun getAllData() {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractorAll() }
    }

    private suspend fun startInteractorAll() {
        _mutableLiveData.postValue(parseSearchResults(interactor.getAllData()))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)//Set View to original state in onStop
        super.onCleared()
    }
}