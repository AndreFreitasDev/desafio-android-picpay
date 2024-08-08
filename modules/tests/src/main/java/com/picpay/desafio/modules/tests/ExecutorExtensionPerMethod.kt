package com.picpay.desafio.modules.tests

import com.picpay.desafio.modules.commons.coroutines.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

@OptIn(ExperimentalCoroutinesApi::class)
class ExecutorExtensionPerMethod : BeforeEachCallback, AfterEachCallback {

    private val dispatcher = StandardTestDispatcher()

    private val dispatcherProvider: DispatcherProvider = TestDispatchers(dispatcher)

    fun testDispatcherProvider() = dispatcherProvider

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(dispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}
