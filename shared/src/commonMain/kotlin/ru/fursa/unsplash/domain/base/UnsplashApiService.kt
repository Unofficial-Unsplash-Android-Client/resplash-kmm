package ru.fursa.unsplash.domain.base

import ru.fursa.unsplash.data.api.models.collection.CollectionResponse
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse
import ru.fursa.unsplash.data.api.models.search.SearchCollectionResponse
import ru.fursa.unsplash.data.api.models.search.SearchPhotoResponse
import ru.fursa.unsplash.data.api.models.search.UserSearchResponse

interface UnsplashApiService {
    suspend fun getCollections(pageIndex: Int = 1, perPage: Int = 5): List<CollectionResponse>
    suspend fun getPhotos(pageIndex: Int = 1, perPage: Int = 5): List<PhotoResponse>
    suspend fun searchPhotos(query: String, pageIndex: Int = 1, perPage: Int = 5): SearchPhotoResponse
    suspend fun searchCollections(query: String, pageIndex: Int = 1, perPage: Int = 5): SearchCollectionResponse
    suspend fun searchUsers(query: String, pageIndex: Int = 1, perPage: Int = 5): UserSearchResponse
}