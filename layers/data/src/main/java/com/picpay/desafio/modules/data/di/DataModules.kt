package com.picpay.desafio.modules.data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.picpay.desafio.modules.data.database.AppDatabase
import com.picpay.desafio.modules.data.database.dao.UserDao
import com.picpay.desafio.modules.data.database.dao.UserDaoImpl
import com.picpay.desafio.modules.data.network.PicPayRepository
import com.picpay.desafio.modules.data.network.PicPayRepositoryImpl
import com.picpay.desafio.modules.data.network.PicPayService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

private const val DEFAULT_CONNECT_TIMEOUT = 60L
private const val DEFAULT_READ_TIMEOUT = 60L
private const val DEFAULT_WRITE_TIMEOUT = 60L
private const val TAG = "PicPay-API"
private const val URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

private const val DB_NAME = "database-picpay"

object DataModules {

    val databaseModule = module {

        single<AppDatabase> {
            val context = get<Context>()
            val builder = Room.databaseBuilder(
                context, AppDatabase::class.java, DB_NAME
            )
            builder.build()
        }

        single<UserDao> {
            UserDaoImpl(get<AppDatabase>().userDao())
        }
    }

    val networkModule = module {

        factory<OkHttpClient> {
            val builder = OkHttpClient.Builder()
                .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)

            val loggingInterceptor = HttpLoggingInterceptor { message ->
                Timber.tag(TAG).d(message)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            builder
                .addInterceptor(loggingInterceptor)
                .build()
        }

        single<Retrofit> {
            val okHttpClient: OkHttpClient = get()
            val gson = GsonBuilder().create()
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(URL)
                .build()
        }

        factory<PicPayService> {
            get<Retrofit>().create(PicPayService::class.java)
        }

        factory<PicPayRepository> {
            PicPayRepositoryImpl(get<PicPayService>())
        }
    }
}