package ru.fursa.unsplash.data.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.fursa.unsplash.domain.base.UnsplashApiService

class PhotoDataSource(
    private val apiService: UnsplashApiService,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getPhotos(pageIndex: Int = 1) = withContext(dispatcher) {
        apiService.getPhotos(pageIndex = pageIndex)
    }
}