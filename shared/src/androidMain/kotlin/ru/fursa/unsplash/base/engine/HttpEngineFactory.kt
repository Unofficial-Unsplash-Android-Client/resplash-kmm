package ru.fursa.unsplash.base.engine

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.okhttp.OkHttpConfig
import okhttp3.OkHttpClient

internal actual class HttpEngineFactory actual constructor() {
    actual fun create(): HttpClientEngineFactory<HttpClientEngineConfig> {
        val client = OkHttpClient.Builder()
            .cache(null)
            .build()

        OkHttpConfig().apply {
            preconfigured = client
        }

        return OkHttp

    }
}



