package ru.fursa.unsplash.domain.base

import ru.fursa.unsplash.data.api.models.base.User
import ru.fursa.unsplash.data.api.models.collection.CollectionResponse
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse
import ru.fursa.unsplash.data.api.models.search.SearchCollectionResponse
import ru.fursa.unsplash.data.api.models.search.SearchPhotoResponse
import ru.fursa.unsplash.data.api.models.search.UserSearchResponse

interface UnsplashApiService {
    suspend fun getCollections(pageIndex: Int = 1, perPage: Int = 30): List<CollectionResponse>
    suspend fun getPhotos(pageIndex: Int = 1, perPage: Int = 30): List<PhotoResponse>
    suspend fun searchPhotos(query: String, pageIndex: Int = 1, perPage: Int = 30): SearchPhotoResponse
    suspend fun searchCollections(query: String, pageIndex: Int = 1, perPage: Int = 30): SearchCollectionResponse
    suspend fun searchUsers(query: String, pageIndex: Int = 1, perPage: Int = 30): UserSearchResponse
    suspend fun getUser(username: String): User
    suspend fun getUserPhotos(username: String, pageIndex: Int = 1, perPage: Int = 30): List<PhotoResponse>
    suspend fun getUserLikes(username: String, pageIndex: Int = 1, perPage: Int = 30): List<PhotoResponse>
    suspend fun getUserCollections(username: String, pageIndex: Int = 1, perPage: Int = 30): List<CollectionResponse>
}