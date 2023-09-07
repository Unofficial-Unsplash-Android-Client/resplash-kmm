package ru.fursa.unsplash.di.modules

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import ru.fursa.unsplash.base.engine.HttpEngineFactory

internal val ktorModule = module {
    single<HttpClient> {
        HttpClient(HttpEngineFactory().create()) {

            install(Logging) {
                logger = io.ktor.client.plugins.logging.Logger.DEFAULT
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        explicitNulls = false
                    }
                )
            }

            defaultRequest {
                url("https://api.unsplash.com")
                header("Accept-Version", "v1")
                header("Authorization", "Client-ID ****")
            }
        }.also { Napier.base(DebugAntilog()) }
    }
}
