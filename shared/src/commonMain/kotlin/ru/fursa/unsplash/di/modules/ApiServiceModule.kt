package ru.fursa.unsplash.di.modules

import org.koin.dsl.module
import ru.fursa.unsplash.data.api.service.UnsplashApiServiceImpl
import ru.fursa.unsplash.domain.base.UnsplashApiService

internal val apiServiceModule = module {
    single<UnsplashApiService> { UnsplashApiServiceImpl(get()) }
}
