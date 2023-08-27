package ru.fursa.unsplash.di.modules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val dispatcherModule = module {
    factory { provideIODispatcher() }
}

private fun provideIODispatcher() = Dispatchers.IO
