package ru.fursa.unsplash.api

import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

actual class UnsplashApiService actual constructor(httpClient: HttpClient) {
    actual suspend fun getCollections(pageIndex: Int): Flow<List<CollectionHttpResponse>> {
       return emptyFlow()
    }
}