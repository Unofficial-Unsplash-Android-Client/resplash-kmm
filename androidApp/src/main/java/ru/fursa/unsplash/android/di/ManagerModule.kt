package ru.fursa.unsplash.android.di

import org.koin.dsl.module
import ru.fursa.unsplash.android.base.eventbus.LoadEventBus

val managerModule = module {
    single { LoadEventBus }
}