package com.picpay.desafio.modules.tests

import com.picpay.desafio.modules.commons.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher

class TestDispatchers(private val dispatcher: CoroutineDispatcher) : DispatcherProvider  {

    override val main: CoroutineDispatcher
        get() = dispatcher
    override val io: CoroutineDispatcher
        get() = dispatcher
    override val default: CoroutineDispatcher
        get() = dispatcher
}