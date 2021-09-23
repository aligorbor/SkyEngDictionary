package ru.geekbrains.android2.skyengdictionary.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.android2.skyengdictionary.data.repository.Repository
import ru.geekbrains.android2.skyengdictionary.data.word.Word
import ru.geekbrains.android2.skyengdictionary.view.main.MainInteractor
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<Word>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<Word>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}
