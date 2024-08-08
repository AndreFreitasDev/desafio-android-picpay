package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.logger.DebugTimberTree
import com.picpay.desafio.modules.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModules)
            allowOverride(true)
        }
        configTimberLogging()
    }

    private fun configTimberLogging() {
        Timber.plant(DebugTimberTree())
    }
}