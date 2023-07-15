package ru.fursa.unsplash.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.fursa.unsplash.api.UnsplashKtorClient

val networkModule = module {
    singleOf(::UnsplashKtorClient)
}