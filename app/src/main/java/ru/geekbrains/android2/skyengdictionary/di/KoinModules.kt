package ru.geekbrains.android2.skyengdictionary.di


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.geekbrains.android2.skyengdictionary.data.datasource.RetrofitImplementation
import ru.geekbrains.android2.skyengdictionary.data.datasource.RoomDataBaseImplementation
import ru.geekbrains.android2.skyengdictionary.data.repository.Repository
import ru.geekbrains.android2.skyengdictionary.data.repository.RepositoryImplementation
import ru.geekbrains.android2.skyengdictionary.data.word.Word
import ru.geekbrains.android2.skyengdictionary.view.main.MainInteractor
import ru.geekbrains.android2.skyengdictionary.view.main.MainViewModel

val application = module {
    single<Repository<List<Word>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<Repository<List<Word>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}
val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    viewModel { MainViewModel(get()) }
}
