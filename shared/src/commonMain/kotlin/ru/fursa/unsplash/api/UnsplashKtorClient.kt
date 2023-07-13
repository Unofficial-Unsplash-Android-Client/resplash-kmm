package ru.fursa.unsplash.api

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
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
            url(BaseApi.BASE_API_URL)
        }
    }.also { Napier.base(DebugAntilog()) }

    suspend fun getCollections(page: Int): List<CollectionHttpResponse> =
        ktorClient.get {
            url {
                appendPathSegments(BaseApi.COLLECTIONS_REQ)
                method = HttpMethod.Get
                parameters.append(BaseApi.PAGE_INDEX_PARAM, page.toString())
                parameters.append(BaseApi.CLIENT_ID_PARAM, BaseApi.UNSPLASH_API_TOKEN)
            }
        }.body()
}
