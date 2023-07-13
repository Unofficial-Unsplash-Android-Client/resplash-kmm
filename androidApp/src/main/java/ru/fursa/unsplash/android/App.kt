package ru.fursa.unsplash.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import ru.fursa.unsplash.di.networkModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(androidContext = this@App)
            modules(networkModule)
        }
    }
}