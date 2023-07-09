package ru.fursa.unsplash

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class UnsplashKtorClient {
    private val ktorClient = HttpClient() {
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
            url("https://api.unsplash.com")
        }
    }.also { Napier.base(DebugAntilog()) }

    suspend fun getCollections(): List<CollectionHttpResponse> {
        val collections: List<CollectionHttpResponse> = ktorClient.get("/collections/?client_id=CLIENT_ID").body()
        return collections
    }
}