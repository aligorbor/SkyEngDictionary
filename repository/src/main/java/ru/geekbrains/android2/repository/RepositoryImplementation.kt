package ru.geekbrains.android2.repository

import ru.geekbrains.android2.model.data.DTO.WordDTO

class RepositoryImplementation(private val dataSource: DataSource<List<WordDTO>>) :
    Repository<List<WordDTO>> {

    override suspend fun getData(word: String): List<WordDTO> {
        return dataSource.getData(word)
    }
}
