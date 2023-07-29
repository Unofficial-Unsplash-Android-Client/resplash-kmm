package ru.fursa.unsplash.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import ru.fursa.unsplash.android.di.viewModelsModule
import ru.fursa.unsplash.di.androidHttpClientModule
import ru.fursa.unsplash.di.apiServiceModule
import ru.fursa.unsplash.di.ktorModule

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(androidContext = this@App)
            modules(
                ktorModule,
                apiServiceModule,
                viewModelsModule,
                androidHttpClientModule
            )
        }
    }
}