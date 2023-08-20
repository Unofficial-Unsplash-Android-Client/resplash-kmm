package ru.fursa.unsplash.base.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.fursa.unsplash.base.Photo
import ru.fursa.unsplash.data.api.models.base.User
import ru.fursa.unsplash.data.api.models.collection.CollectionResponse
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse

interface UnsplashRepository {
    fun getCollections(): Flow<PagingData<CollectionResponse>>
    fun getPhotos(): Flow<PagingData<Photo>>
    fun searchPhotos(query: String): Flow<PagingData<PhotoResponse>>
    fun searchCollection(query: String): Flow<PagingData<CollectionResponse>>
    fun searchUsers(query: String): Flow<PagingData<User>>
}