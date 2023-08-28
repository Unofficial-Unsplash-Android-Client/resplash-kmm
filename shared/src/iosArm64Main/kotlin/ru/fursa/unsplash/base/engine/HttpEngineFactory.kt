package ru.fursa.unsplash.base.engine

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
internal actual class HttpEngineFactory actual constructor() {
    actual fun create(): HttpClientEngineFactory<HttpClientEngineConfig> {
        return Darwin
    }
}
