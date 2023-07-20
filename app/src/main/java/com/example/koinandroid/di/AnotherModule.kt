package com.example.koinandroid.di

import com.example.koinandroid.view.ListFragment
import org.koin.core.qualifier.named
import org.koin.dsl.module

val anotherModule = module {

    scope<ListFragment> {
        scoped(qualifier = named("hello")){
            "Hello Tamerlan!"
        }

        scoped(qualifier = named("hi")){
            "Hi Kotlin!"
        }
    }
}