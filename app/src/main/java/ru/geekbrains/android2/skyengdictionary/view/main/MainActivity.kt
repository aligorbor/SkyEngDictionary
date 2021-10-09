package ru.geekbrains.android2.skyengdictionary.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityRetainedScope
import org.koin.core.scope.Scope
import ru.geekbrains.android2.core.BaseActivity
import ru.geekbrains.android2.descriptionscreen.DescriptionActivity
import ru.geekbrains.android2.historyscreen.view.history.HistoryActivity
import ru.geekbrains.android2.model.data.AppState
import ru.geekbrains.android2.model.data.Word
import ru.geekbrains.android2.skyengdictionary.R
import ru.geekbrains.android2.skyengdictionary.databinding.ActivityMainBinding
import ru.geekbrains.android2.skyengdictionary.utils.convertMeaningsToString
import ru.geekbrains.android2.skyengdictionary.view.main.adapter.MainAdapter
import ru.geekbrains.android2.utils.OnlineLiveData
import ru.geekbrains.android2.utils.viewById

class MainActivity : BaseActivity<AppState, MainInteractor>(), AndroidScopeComponent {

    private lateinit var binding: ActivityMainBinding
    override val scope: Scope by activityRetainedScope()
    override lateinit var model: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val mainActivityRecyclerview by viewById<RecyclerView>(R.id.main_activity_recyclerview)
    private val searchFab by viewById<FloatingActionButton>(R.id.search_fab)
    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            fromRemote = true
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: Word) = showDescription(data)
        }
    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                if (isNetworkAvailable) {
                    model.getData(searchWord, fromRemote)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    private var fromRemote = true

    private lateinit var snackBar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeToNetworkChange()
        initViewModel()
        initViews()
    }

    private fun showDescription(data: Word) {
        startActivity(
            DescriptionActivity.getIntent(
                this@MainActivity,
                data.text,
                convertMeaningsToString(data.meanings),
                data.meanings[0].imageUrl
            )
        )
    }

    override fun setDataToAdapter(data: List<Word>) {
        adapter.setData(data)
        if (!fromRemote) showDescription(data = data[0])
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            R.id.menu_search_room -> {
                fromRemote = false
                val searchDialogFragment = SearchDialogFragment.newInstance()
                searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
                searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViewModel() {
        if (mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: MainViewModel by inject()
        model = viewModel
        model.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    private fun initViews() {
        searchFab.setOnClickListener(fabClickListener)
        mainActivityRecyclerview.adapter = adapter
    }

    fun subscribeToNetworkChange() {
        snackBar = Snackbar.make(
            binding.root,
            R.string.dialog_message_device_is_offline,
            Snackbar.LENGTH_INDEFINITE
        )
        OnlineLiveData(this).observe(
            this,
            {
                isNetworkAvailable = it
                if (!isNetworkAvailable)
                    snackBar.show()
                else snackBar.dismiss()

            }
        )
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

}