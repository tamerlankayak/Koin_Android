package com.example.koinandroid

import android.app.Application
import com.example.koinandroid.di.anotherModule
import com.example.koinandroid.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule, anotherModule)
        }
    }
}