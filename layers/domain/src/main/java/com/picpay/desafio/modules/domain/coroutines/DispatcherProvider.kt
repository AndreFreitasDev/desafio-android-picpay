package com.picpay.desafio.modules.domain.coroutines

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class DispatcherProvider(
    private val coroutineContext: CoroutineContext? = null
) {
    fun main(): CoroutineContext = coroutineContext ?: Dispatchers.Main

    fun io(): CoroutineContext = coroutineContext ?: Dispatchers.IO
}