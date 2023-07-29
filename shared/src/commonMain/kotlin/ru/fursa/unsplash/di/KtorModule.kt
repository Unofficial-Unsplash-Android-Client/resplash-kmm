package ru.fursa.unsplash.di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import ru.fursa.unsplash.api.BaseApi
import ru.fursa.unsplash.engine.HttpEngineFactory

val ktorModule = module {
    single<HttpClient> {
        HttpClient(HttpEngineFactory().create()) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v("Ktor Http Client", null, message)
                    }
                }
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            defaultRequest {
                url(BaseApi.BASE_API_URL)
            }
        }.also { Napier.base(DebugAntilog()) }
    }
}
