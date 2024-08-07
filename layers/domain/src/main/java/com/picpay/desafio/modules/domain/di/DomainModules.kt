package com.picpay.desafio.modules.domain.di

import com.picpay.desafio.modules.domain.coroutines.DispatcherProvider
import com.picpay.desafio.modules.domain.useCases.GetCacheOfUsersUseCase
import com.picpay.desafio.modules.domain.useCases.GetUsersUseCase
import com.picpay.desafio.modules.domain.useCases.SaveCacheOfUsersUseCase
import org.koin.dsl.module

object DomainModules {

    val module = module {
        factory {
            DispatcherProvider()
        }
    }

    val useCasesModule = module {
        factory {
            GetUsersUseCase(
                saveCacheOfUsersUseCase = get(),
                getCacheOfUsersUseCase = get(),
                repository = get(),
                dispatcher = get()
            )
        }
        factory {
            GetCacheOfUsersUseCase(
                userDao = get(),
                dispatcher = get()
            )
        }
        factory {
            SaveCacheOfUsersUseCase(
                userDao = get(),
                dispatcher = get()
            )
        }
    }
}