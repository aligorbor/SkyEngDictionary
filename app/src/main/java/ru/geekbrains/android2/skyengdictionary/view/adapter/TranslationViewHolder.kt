package ru.geekbrains.android2.skyengdictionary.view.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.geekbrains.android2.skyengdictionary.databinding.ViewTranslationBinding
import ru.geekbrains.android2.skyengdictionary.view.TranslationViewModel

class TranslationViewHolder(val binding: ViewTranslationBinding) : ViewHolder(binding.root) {

    fun bind(translation: TranslationViewModel, delegate: TranslationsAdapter.Delegate?) {
        with(binding) {
            translationText.text = translation.text
            root.setOnClickListener {
                delegate?.onTranslationPicked(translation)
            }
        }
    }

}