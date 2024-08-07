package com.picpay.desafio.modules.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.libraries.commons.StringValue
import com.picpay.desafio.modules.domain.model.User
import com.picpay.desafio.modules.domain.useCases.GetUsersUseCase
import com.picpay.desafio.modules.presentation.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    private val usersLiveData = MutableLiveData<List<User>>()

    private val _state = MutableStateFlow<MainViewState>(MainViewState.Nothing)
    val state: StateFlow<MainViewState> = _state

    fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.LoadUsers -> {
                loadUsers()
            }
        }
    }

    private fun MainViewState.sendState() {
        _state.value = this
    }

    private fun loadUsers() {
        val usersCache: List<User> = usersLiveData.value.orEmpty()
        if (usersCache.isNotEmpty()) {
            MainViewState.UsersLoaded(usersCache).sendState()
            return
        }
        viewModelScope.launch {
            MainViewState.ShowLoading.sendState()
            kotlin.runCatching {
                getUsersUseCase.execute().apply {
                    usersLiveData.value = this
                }
            }.onSuccess { users ->
                MainViewState.HideLoading.sendState()
                MainViewState.UsersLoaded(users).sendState()
            }.onFailure { error ->
                MainViewState.HideLoading.sendState()
                MainViewState
                    .PresentError(StringValue.StringResource(R.string.error))
                    .sendState()
                Timber.tag("MAIN").e(error)
            }
        }
    }
}