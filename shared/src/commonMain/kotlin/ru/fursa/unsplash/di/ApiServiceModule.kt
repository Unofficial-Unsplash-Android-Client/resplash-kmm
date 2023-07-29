package ru.fursa.unsplash.di

import org.koin.dsl.module
import ru.fursa.unsplash.data.api.service.UnsplashApiServiceImpl
import ru.fursa.unsplash.domain.base.UnsplashApiService

val apiServiceModule = module {
    single<UnsplashApiService> { UnsplashApiServiceImpl(get()) }
}