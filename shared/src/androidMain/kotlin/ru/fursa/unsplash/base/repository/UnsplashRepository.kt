package ru.fursa.unsplash.base.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.fursa.unsplash.data.ui.models.CollectionModel
import ru.fursa.unsplash.data.ui.models.PhotoModel
import ru.fursa.unsplash.data.ui.models.UserModel

interface UnsplashRepository {
    val userFlow: StateFlow<CurrentUser>
    fun getCollections(): Flow<PagingData<CollectionModel>>
    suspend fun getPhotos(pageIndex: Int = 1): List<PhotoModel>
    fun searchPhotos(query: String): Flow<PagingData<PhotoModel>>
    fun searchCollection(query: String): Flow<PagingData<CollectionModel>>
    fun searchUsers(query: String): Flow<PagingData<UserModel>>
    suspend fun getUser(username: String)
    suspend fun getUserPhotos(username: String): Flow<PagingData<PhotoModel>>
    suspend fun getUserLikes(username: String): Flow<PagingData<PhotoModel>>
    suspend fun getUserCollections(username: String): Flow<PagingData<CollectionModel>>
    suspend fun getCollectionPhotos(id: String): Flow<PagingData<PhotoModel>>
}
