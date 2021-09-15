package ru.geekbrains.android2.skyengdictionary.view

interface BaseView {
    fun setTranslationsForSearchWord(translations: List<TranslationViewModel>)
    fun showError(error: Throwable)
}