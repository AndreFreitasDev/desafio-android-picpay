package com.picpay.desafio.modules.presentation.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.modules.presentation.databinding.ActivityMainBinding
import com.picpay.desafio.modules.presentation.ui.main.adapter.UserListAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModel()

    private val adapter: UserListAdapter by lazy { UserListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupAdapter()
        setupObservers()
        viewModel.handleIntent(MainIntent.LoadUsers)
    }

    private fun setupAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect(::handlerState)
            }
        }
    }

    private fun handlerState(state: MainViewState) {
        when (state) {
            is MainViewState.Nothing -> {}
            is MainViewState.UsersLoaded -> {
                adapter.addItems(state.users)
                with(binding) {
                    recyclerView.visibility = View.VISIBLE
                    containerError.visibility = View.GONE
                }
            }
            is MainViewState.ShowLoading -> {
                with(binding) {
                    recyclerView.visibility = View.GONE
                    containerError.visibility = View.GONE
                    progress.visibility = View.VISIBLE
                }
            }
            is MainViewState.HideLoading -> {
                with(binding) {
                    progress.visibility = View.GONE
                }
            }
            is MainViewState.PresentError -> {
                val text = state.message.asString(this)
                with(binding) {
                    recyclerView.visibility = View.GONE
                    progress.visibility = View.GONE
                    containerError.visibility = View.VISIBLE
                    messageError.text = text
                    buttonRetry.setOnClickListener {
                        viewModel.handleIntent(MainIntent.LoadUsers)
                    }
                }
            }
        }
    }
}
