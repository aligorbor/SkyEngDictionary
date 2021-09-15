package ru.geekbrains.android2.skyengdictionary.presenter

import android.app.Activity

interface BasePresenter {
    fun attachActivity(activity: Activity)
    fun detachActivity()
    fun getTranslation(searchWord: String)
}