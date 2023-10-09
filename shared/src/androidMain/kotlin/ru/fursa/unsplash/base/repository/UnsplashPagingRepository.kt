package ru.fursa.unsplash.base.repository

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import ru.fursa.unsplash.base.mappers.toCurrentUser
import ru.fursa.unsplash.base.mappers.toUiCollections
import ru.fursa.unsplash.base.mappers.toUiPhoto
import ru.fursa.unsplash.base.mappers.toUiPhotos
import ru.fursa.unsplash.base.mappers.toUiUsers
import ru.fursa.unsplash.base.paging.finitePager
import ru.fursa.unsplash.base.paging.infinitePager
import ru.fursa.unsplash.data.api.PhotoDataSource
import ru.fursa.unsplash.data.ui.models.CollectionModel
import ru.fursa.unsplash.data.ui.models.PhotoModel
import ru.fursa.unsplash.data.ui.models.UserModel
import ru.fursa.unsplash.domain.base.UnsplashApiService
import ru.fursa.unsplash.utils.ResourceProvider

class UnsplashPagingRepository(
    private val apiService: UnsplashApiService,
    private val ioDispatcher: CoroutineDispatcher,
    private val resourceProvider: ResourceProvider,
    private val photoDataSource: PhotoDataSource,
) : UnsplashRepository {

    private val _userFlow = MutableStateFlow(
        value = CurrentUser()
    )
    override val userFlow: StateFlow<CurrentUser>
        get() = _userFlow.asStateFlow()

    override fun getCollections(): Flow<PagingData<CollectionModel>> = infinitePager { index ->
        apiService.getCollections(index).toUiCollections()
    }.flow

    override suspend fun getPhotos(pageIndex: Int): List<PhotoModel> =
        photoDataSource.getPhotos(pageIndex = pageIndex).map { it.toUiPhoto() }


    override fun searchPhotos(query: String): Flow<PagingData<PhotoModel>> =
        infinitePager { index ->
            apiService.searchPhotos(query = query, pageIndex = index).results.toUiPhotos()
        }.flow

    override fun searchCollection(query: String): Flow<PagingData<CollectionModel>> =
        infinitePager { index ->
            apiService.searchCollections(query = query, pageIndex = index).results.toUiCollections()
        }.flow

    override fun searchUsers(query: String): Flow<PagingData<UserModel>> = infinitePager { index ->
        apiService.searchUsers(query = query, pageIndex = index).results.toUiUsers()
    }.flow

    override suspend fun getUserPhotos(
        username: String,
    ): Flow<PagingData<PhotoModel>> = finitePager { index ->
        apiService.getUserPhotos(username = username, pageIndex = index).toUiPhotos()
    }.flow

    override suspend fun getUserLikes(
        username: String,
    ): Flow<PagingData<PhotoModel>> = finitePager { index ->
        apiService.getUserLikes(username = username, pageIndex = index).toUiPhotos()
    }.flow

    override suspend fun getUserCollections(
        username: String,
    ): Flow<PagingData<CollectionModel>> =
        finitePager { index ->
            apiService.getUserCollections(username = username, pageIndex = index).toUiCollections()
        }.flow

    override suspend fun getCollectionPhotos(id: String): Flow<PagingData<PhotoModel>> =
        finitePager { index ->
            apiService.getCollection(id, pageIndex = index).toUiPhotos()
        }.flow

    override suspend fun getUser(username: String) = withContext(ioDispatcher) {
        val user = apiService.getUser(username).toCurrentUser()
        _userFlow.emit(user)
    }
}
