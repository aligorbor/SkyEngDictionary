package ru.geekbrains.android2.skyengdictionary.di


import androidx.room.Room
import org.koin.dsl.module
import ru.geekbrains.android2.skyengdictionary.data.datasource.RetrofitImplementation
import ru.geekbrains.android2.skyengdictionary.data.datasource.RoomDataBaseImplementation
import ru.geekbrains.android2.skyengdictionary.data.repository.Repository
import ru.geekbrains.android2.skyengdictionary.data.repository.RepositoryImplementation
import ru.geekbrains.android2.skyengdictionary.data.repository.RepositoryImplementationLocal
import ru.geekbrains.android2.skyengdictionary.data.repository.RepositoryLocal
import ru.geekbrains.android2.skyengdictionary.data.room.HistoryDataBase
import ru.geekbrains.android2.skyengdictionary.data.word.Word
import ru.geekbrains.android2.skyengdictionary.view.history.HistoryInteractor
import ru.geekbrains.android2.skyengdictionary.view.history.HistoryViewModel
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
