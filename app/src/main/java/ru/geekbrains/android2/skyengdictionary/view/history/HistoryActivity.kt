package ru.geekbrains.android2.skyengdictionary.view.history

import android.os.Bundle
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.android2.skyengdictionary.data.AppState
import ru.geekbrains.android2.skyengdictionary.data.word.Word
import ru.geekbrains.android2.skyengdictionary.databinding.ActivityHistoryBinding
import ru.geekbrains.android2.skyengdictionary.utils.convertMeaningsToString
import ru.geekbrains.android2.skyengdictionary.view.base.BaseActivity
import ru.geekbrains.android2.skyengdictionary.view.description.DescriptionActivity
import ru.geekbrains.android2.skyengdictionary.view.main.adapter.MainAdapter

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter(onListItemClickListener) }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: Word) = showDescription(data)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getAllData()
    }

    override fun setDataToAdapter(data: List<Word>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> { renderData(it) })
    }

    private fun showDescription(data: Word) {
        startActivity(
            DescriptionActivity.getIntent(
                this@HistoryActivity,
                data.text!!,
                convertMeaningsToString(data.meanings!!),
                data.meanings[0].imageUrl
            )
        )
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}