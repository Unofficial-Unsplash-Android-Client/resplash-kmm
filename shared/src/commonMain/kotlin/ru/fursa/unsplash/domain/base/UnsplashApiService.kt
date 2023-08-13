package ru.fursa.unsplash.domain.base

import ru.fursa.unsplash.data.api.models.collection.CollectionResponse
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse

interface UnsplashApiService {
    suspend fun getCollections(pageIndex: Int = 1, perPage: Int = 30): List<CollectionResponse>
    suspend fun getPhotos(pageIndex: Int = 1, perPage: Int = 30): List<PhotoResponse>
}