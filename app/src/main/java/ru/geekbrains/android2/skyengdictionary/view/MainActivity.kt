package ru.geekbrains.android2.skyengdictionary.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.android2.skyengdictionary.App
import ru.geekbrains.android2.skyengdictionary.databinding.ActivityMainBinding
import ru.geekbrains.android2.skyengdictionary.presenter.BasePresenter
import ru.geekbrains.android2.skyengdictionary.view.adapter.TranslationsAdapter

class MainActivity : AppCompatActivity(), BaseView, TranslationsAdapter.Delegate {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: BasePresenter
    private val translationAdapter = TranslationsAdapter(delegate = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = App.instance.mainPresenter
        presenter.attachActivity(this)
        binding.translations.adapter = translationAdapter
        binding.inputLayoutSubject.setEndIconOnClickListener {
            presenter.getTranslation(binding.searchWord.text.toString())
        }
    }

    override fun setTranslationsForSearchWord(translations: List<TranslationViewModel>) {
        translationAdapter.submitList(translations)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.detachActivity()
        super.onDestroy()
    }

    override fun onTranslationPicked(translation: TranslationViewModel) {
        Toast.makeText(this, translation.text, Toast.LENGTH_SHORT).show()
    }
}