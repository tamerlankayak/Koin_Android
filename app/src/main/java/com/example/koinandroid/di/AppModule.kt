package com.example.koinandroid.di

import com.example.koinandroid.repository.CryptoDownload
import com.example.koinandroid.repository.CryptoDownloadImpl
import com.example.koinandroid.service.CryptoAPI
import com.example.koinandroid.viewmodel.CryptoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        val BASE_URL = "https://raw.githubusercontent.com/"

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)
    }

    single<CryptoDownload> {
        CryptoDownloadImpl(get())
    }

    viewModel {
        CryptoViewModel(get())
    }
}