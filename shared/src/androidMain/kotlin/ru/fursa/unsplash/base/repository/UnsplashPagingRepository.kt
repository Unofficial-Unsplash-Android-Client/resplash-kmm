package ru.fursa.unsplash.base.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.fursa.unsplash.base.paging.infinitePager
import ru.fursa.unsplash.data.api.models.collection.CollectionResponse
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse
import ru.fursa.unsplash.domain.base.UnsplashApiService

class UnsplashPagingRepository(
    private val apiService: UnsplashApiService
): UnsplashRepository {


    override fun getCollections(): Flow<PagingData<CollectionResponse>> = infinitePager { index ->
        apiService.getCollections(index)
    }.flow

    override fun getPhotos(): Flow<PagingData<PhotoResponse>> = infinitePager { index ->
        apiService.getPhotos(index)
    }.flow
}