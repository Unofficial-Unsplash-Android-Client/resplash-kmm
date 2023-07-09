package ru.fursa.unsplash

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
            logger = Logger.DEFAULT
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
    }

    suspend fun getCollections(): List<CollectionHttpResponse> {
        val collections: List<CollectionHttpResponse> = ktorClient.get("/collections/?client_id=CLIENT_KEY").body()
        return collections
    }
}