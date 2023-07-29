package ru.fursa.unsplash.di

import org.koin.dsl.module
import ru.fursa.unsplash.api.UnsplashApiService

val apiServiceModule = module {
    single { UnsplashApiService(get()) }
}