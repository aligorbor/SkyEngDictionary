package ru.geekbrains.android2.skyengdictionary.di


import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.geekbrains.android2.historyscreen.view.history.HistoryActivity
import ru.geekbrains.android2.historyscreen.view.history.HistoryInteractor
import ru.geekbrains.android2.historyscreen.view.history.HistoryViewModel
import ru.geekbrains.android2.model.data.DTO.WordDTO
import ru.geekbrains.android2.repository.*
import ru.geekbrains.android2.repository.room.HistoryDataBase
import ru.geekbrains.android2.skyengdictionary.view.main.MainActivity
import ru.geekbrains.android2.skyengdictionary.view.main.MainInteractor
import ru.geekbrains.android2.skyengdictionary.view.main.MainViewModel

val application = module {
    single {
        Room.databaseBuilder(get(), HistoryDataBase::class.java, HistoryDataBase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<WordDTO>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<WordDTO>>> {
        RepositoryImplementationLocal(
            RoomDataBaseImplementation(
                get()
            )
        )
    }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }

}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        scoped { HistoryInteractor(get(), get()) }
        viewModel { HistoryViewModel(get()) }
    }

}
