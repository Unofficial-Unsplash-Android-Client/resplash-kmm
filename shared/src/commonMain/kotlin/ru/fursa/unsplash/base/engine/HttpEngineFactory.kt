package ru.fursa.unsplash.base.engine

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

internal expect class HttpEngineFactory constructor() {
    fun create(): HttpClientEngineFactory<HttpClientEngineConfig>
}
