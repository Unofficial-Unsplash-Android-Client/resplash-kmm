package ru.fursa.unsplash.base.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.fursa.unsplash.base.utils.AndroidResourceProvider
import ru.fursa.unsplash.utils.ResourceProvider

val resourceModule = module {
    single<ResourceProvider> { AndroidResourceProvider(androidContext()) }
}