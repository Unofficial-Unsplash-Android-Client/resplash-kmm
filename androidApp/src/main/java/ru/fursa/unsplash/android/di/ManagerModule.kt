package ru.fursa.unsplash.android.di

import android.app.WallpaperManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import ru.fursa.unsplash.android.base.ImageDownloader
import ru.fursa.unsplash.android.base.eventbus.LoadEventBus

val managerModule = module {
    single { LoadEventBus }
}

val wallpaperManagerModule = module {
    single { WallpaperManager.getInstance(androidApplication()) }
}

val downloaderModule = module {
    single { ImageDownloader(androidApplication(), get()) }
}
