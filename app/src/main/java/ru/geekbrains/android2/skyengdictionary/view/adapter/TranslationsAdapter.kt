package ru.geekbrains.android2.skyengdictionary.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.geekbrains.android2.skyengdictionary.view.TranslationViewModel
import ru.geekbrains.android2.skyengdictionary.R.layout.view_translation
import ru.geekbrains.android2.skyengdictionary.databinding.ViewTranslationBinding

class TranslationsAdapter(private val delegate: Delegate?) :
    ListAdapter<TranslationViewModel, TranslationViewHolder>(TranslationDiff) {
    interface Delegate {
        fun onTranslationPicked(translation: TranslationViewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationViewHolder =
        TranslationViewHolder(
            ViewTranslationBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: TranslationViewHolder, position: Int) =
        holder.bind(getItem(position), delegate)

}