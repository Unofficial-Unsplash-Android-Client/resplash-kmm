package ru.fursa.unsplash.di

import org.koin.dsl.module
import ru.fursa.unsplash.api.UnsplashApiService

val androidHttpClientModule = module {
    single { UnsplashApiService(get()) }
}