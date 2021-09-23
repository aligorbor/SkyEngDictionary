package ru.geekbrains.android2.skyengdictionary.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.android2.skyengdictionary.data.datasource.DataSource
import ru.geekbrains.android2.skyengdictionary.data.datasource.RetrofitImplementation
import ru.geekbrains.android2.skyengdictionary.data.datasource.RoomDataBaseImplementation
import ru.geekbrains.android2.skyengdictionary.data.repository.Repository
import ru.geekbrains.android2.skyengdictionary.data.repository.RepositoryImplementation
import ru.geekbrains.android2.skyengdictionary.data.word.Word
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<Word>>): Repository<List<Word>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<Word>>): Repository<List<Word>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<Word>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<Word>> = RoomDataBaseImplementation()
}
