package ru.fursa.unsplash.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import ru.fursa.unsplash.android.di.downloaderModule
import ru.fursa.unsplash.android.di.managerModule
import ru.fursa.unsplash.android.di.viewModelsModule
import ru.fursa.unsplash.android.di.wallpaperManagerModule
import ru.fursa.unsplash.base.di.repositoryModule
import ru.fursa.unsplash.base.di.resourceModule
import ru.fursa.unsplash.di.networkModules

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(androidContext = this@App)
            modules(
                viewModelsModule,
                repositoryModule,
                managerModule,
                resourceModule,
                wallpaperManagerModule,
                downloaderModule
            )
            modules(networkModules)
        }
    }
}
