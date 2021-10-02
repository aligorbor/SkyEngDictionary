package ru.geekbrains.android2.skyengdictionary.di


import androidx.room.Room
import org.koin.dsl.module
import ru.geekbrains.android2.historyscreen.view.history.HistoryInteractor
import ru.geekbrains.android2.historyscreen.view.history.HistoryViewModel
import ru.geekbrains.android2.model.data.Word
import ru.geekbrains.android2.repository.*
import ru.geekbrains.android2.repository.room.HistoryDataBase
import ru.geekbrains.android2.skyengdictionary.view.main.MainInteractor
import ru.geekbrains.android2.skyengdictionary.view.main.MainViewModel

val application = module {
    single {
        Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<Word>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<Word>>> {
        RepositoryImplementationLocal(
            RoomDataBaseImplementation(
                get()
            )
        )
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}
