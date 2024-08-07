package com.picpay.desafio.modules.di

import com.picpay.desafio.modules.data.di.DataModules
import com.picpay.desafio.modules.domain.di.DomainModules
import com.picpay.desafio.modules.presentation.di.PresentationModules

val appModules = listOf(
    DataModules.databaseModule,
    DataModules.networkModule,
    DomainModules.module,
    DomainModules.useCasesModule,
    PresentationModules.viewModelModule
)