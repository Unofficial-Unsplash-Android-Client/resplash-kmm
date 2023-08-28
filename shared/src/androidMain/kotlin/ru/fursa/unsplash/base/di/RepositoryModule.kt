package ru.fursa.unsplash.base.di

import org.koin.dsl.module
import ru.fursa.unsplash.base.repository.UnsplashPagingRepository
import ru.fursa.unsplash.base.repository.UnsplashRepository

val repositoryModule = module {
    single<UnsplashRepository> {
        UnsplashPagingRepository(
            apiService = get(),
            ioDispatcher = get(),
            resourceProvider = get()
        )
    }
}
