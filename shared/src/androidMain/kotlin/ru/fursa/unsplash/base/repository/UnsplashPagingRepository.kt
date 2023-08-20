package ru.fursa.unsplash.base.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.fursa.unsplash.base.Photo
import ru.fursa.unsplash.base.mappers.toUi
import ru.fursa.unsplash.base.paging.infinitePager
import ru.fursa.unsplash.data.api.models.collection.CollectionResponse
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse
import ru.fursa.unsplash.data.api.models.photo.User
import ru.fursa.unsplash.domain.base.UnsplashApiService

class UnsplashPagingRepository(
    private val apiService: UnsplashApiService
): UnsplashRepository {


    override fun getCollections(): Flow<PagingData<CollectionResponse>> = infinitePager { index ->
        apiService.getCollections(index)
    }.flow

    override fun getPhotos(): Flow<PagingData<Photo>> = infinitePager { index ->
        apiService.getPhotos(index).map { response -> response.toUi() }
    }.flow

    override fun searchPhotos(query: String): Flow<PagingData<PhotoResponse>> = infinitePager { index ->
        apiService.searchPhotos(query = query, pageIndex = index).results
    }.flow

    override fun searchCollection(query: String): Flow<PagingData<CollectionResponse>> = infinitePager {index ->
        apiService.searchCollections(query = query, pageIndex = index).results
    }.flow

    override fun searchUsers(query: String): Flow<PagingData<User>> = infinitePager { index ->
        apiService.searchUsers(query = query, pageIndex = index).results
    }.flow
}