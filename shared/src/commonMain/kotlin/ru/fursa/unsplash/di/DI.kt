package ru.fursa.unsplash.di

import ru.fursa.unsplash.di.modules.apiServiceModule
import ru.fursa.unsplash.di.modules.dispatcherModule
import ru.fursa.unsplash.di.modules.ktorModule

val networkModules = listOf(
    ktorModule,
    apiServiceModule,
    dispatcherModule
)