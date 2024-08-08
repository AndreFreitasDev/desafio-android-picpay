package com.picpay.desafio.android

import android.app.Application
import androidx.test.runner.AndroidJUnitRunner
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CustomTestInstrumentationRunner : AndroidJUnitRunner() {

    companion object {
        const val MOCK_WEB_SERVER_PORT = 4007
    }

    override fun callApplicationOnCreate(app: Application?) {
        super.callApplicationOnCreate(app)
        app?.let { context ->
            val retrofitModule = module {
                single<Retrofit> {
                    val okHttpClient: OkHttpClient = get()
                    val gson = GsonBuilder().create()
                    Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(okHttpClient)
                        .baseUrl("http://localhost:$MOCK_WEB_SERVER_PORT/")
                        .build()
                }
            }
            loadKoinModules(retrofitModule)
        }
    }
}