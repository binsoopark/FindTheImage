package net.joyfulworld.base.di

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val baseNetworkKoinModule =
    module {
        single {
            Retrofit.Builder()
                .baseUrl("https://pixabay.com") //https://pixabay.com/api/?key=17434116-8fedc09ffd818b7d332eb8d20&q=열매
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
        }

        single {
            Cache(androidApplication().cacheDir, 10L * 1024 * 1024)
        }

        single {
            OkHttpClient.Builder().apply {
                cache(get())
                connectTimeout(30L, TimeUnit.SECONDS)
                writeTimeout(30L, TimeUnit.SECONDS)
                readTimeout(30L, TimeUnit.SECONDS)
                retryOnConnectionFailure(true)
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC // TODO::Debug 일때는 body로 되도록 바꾸자
                }).build()
            }
        }
    }