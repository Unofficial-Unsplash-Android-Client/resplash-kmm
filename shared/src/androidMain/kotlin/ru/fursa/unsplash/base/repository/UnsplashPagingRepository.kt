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
import ru.fursa.unsplash.base.paging.infinitePager
import ru.fursa.unsplash.data.ui.models.CollectionModel
import ru.fursa.unsplash.data.ui.models.PhotoModel
import ru.fursa.unsplash.data.ui.models.UserModel
import ru.fursa.unsplash.domain.base.UnsplashApiService

class UnsplashPagingRepository(
    private val apiService: UnsplashApiService,
    private val dispatcher: CoroutineDispatcher
) : UnsplashRepository {

    private val _userFlow = MutableStateFlow(
        value = CurrentUser(
            username = "",
            fullName = "",
            location = "",
            bio = "",
            totalLikes = 0,
            totalPhotos = 0,
            totalCollections = 0,
            userAvatarUrl = ""
        )
    )
    override val userFlow: StateFlow<CurrentUser>
        get() = _userFlow.asStateFlow()

    override fun getCollections(): Flow<PagingData<CollectionModel>> = infinitePager { index ->
        apiService.getCollections(index).toUiCollections()
    }.flow

    override fun getPhotos(): Flow<PagingData<PhotoModel>> = infinitePager { index ->
        apiService.getPhotos(index).map { response -> response.toUiPhoto() }
    }.flow

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

    override suspend fun getUser(username: String) = withContext(dispatcher) {
        val user = apiService.getUser(username).toCurrentUser()
        _userFlow.emit(user)
    }
}