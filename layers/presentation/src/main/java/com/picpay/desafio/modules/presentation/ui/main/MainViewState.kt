package com.picpay.desafio.modules.presentation.ui.main

import com.picpay.desafio.modules.commons.resources.StringValue
import com.picpay.desafio.modules.domain.model.User

sealed interface MainViewState {
    data object Nothing : MainViewState
    data object ShowLoading : MainViewState
    data object HideLoading : MainViewState
    data class PresentError(val message: StringValue) : MainViewState
    data class UsersLoaded(val users: List<User>) : MainViewState
}