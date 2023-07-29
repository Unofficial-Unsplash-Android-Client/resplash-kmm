package ru.fursa.unsplash.engine

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

expect class HttpEngineFactory constructor(){
    fun create(): HttpClientEngineFactory<HttpClientEngineConfig>
}