package ru.geekbrains.android2.skyengdictionary.data

import ru.geekbrains.android2.skyengdictionary.data.api.SkyEngApiFactory

object BaseReposDataFactory {
    fun create(): BaseReposData =
        BaseReposDataImpl(
            SkyEngApiFactory.create()
        )
}