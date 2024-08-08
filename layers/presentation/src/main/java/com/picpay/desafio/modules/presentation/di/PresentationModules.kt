package com.picpay.desafio.modules.presentation.di

import com.picpay.desafio.modules.presentation.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModules {

    val viewModelModule = module {
        viewModel {
            MainViewModel(
                getUsersUseCase = get(),
                dispatcher = get()
            )
        }
    }
}