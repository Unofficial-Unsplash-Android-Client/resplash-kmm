package ru.fursa.unsplash.base.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.fursa.unsplash.data.api.models.collection.CollectionResponse
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse

interface UnsplashRepository {
    fun getCollections(): Flow<PagingData<CollectionResponse>>
    fun getPhotos(): Flow<PagingData<PhotoResponse>>
}