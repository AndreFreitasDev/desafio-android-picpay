package com.picpay.desafio.modules.presentation.ui.main

sealed interface MainIntent {
    data object LoadUsers: MainIntent
}