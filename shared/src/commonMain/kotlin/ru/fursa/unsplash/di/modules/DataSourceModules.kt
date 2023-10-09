package ru.fursa.unsplash.di.modules

import org.koin.dsl.module
import ru.fursa.unsplash.data.api.PhotoDataSource

val dataSourceModules = module {
    factory {
        PhotoDataSource(
            apiService = get(),
            dispatcher = get()
        )
    }
}