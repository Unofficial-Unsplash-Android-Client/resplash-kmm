package ru.fursa.unsplash.android.di

import android.app.WallpaperManager
import coil.ImageLoader
import coil.request.ImageRequest
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.fursa.unsplash.android.base.eventbus.LoadEventBus

val managerModule = module {
    single { LoadEventBus }
    single { ImageRequest.Builder(androidContext()) }
    single { ImageLoader.Builder(androidContext()) }
    single { WallpaperManager.getInstance(androidApplication()) }
}
