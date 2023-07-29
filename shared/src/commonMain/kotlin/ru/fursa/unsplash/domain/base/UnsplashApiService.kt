package ru.fursa.unsplash.domain.base

import ru.fursa.unsplash.data.api.models.collection.CollectionHttpResponse

interface UnsplashApiService {
    suspend fun getCollections(pageIndex: Int): List<CollectionHttpResponse>
}