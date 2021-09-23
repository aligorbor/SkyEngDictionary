package ru.geekbrains.android2.skyengdictionary.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.geekbrains.android2.skyengdictionary.view.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
