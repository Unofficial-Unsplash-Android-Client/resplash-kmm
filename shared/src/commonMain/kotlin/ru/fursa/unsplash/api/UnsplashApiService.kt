package ru.fursa.unsplash.api

import io.ktor.client.HttpClient


expect class UnsplashApiService constructor(httpClient: HttpClient) {
    suspend fun getCollections(pageIndex: Int): List<CollectionHttpResponse>
}
