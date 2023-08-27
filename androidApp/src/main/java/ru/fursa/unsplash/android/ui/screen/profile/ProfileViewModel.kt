package ru.fursa.unsplash.android.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.fursa.unsplash.base.repository.CurrentUser
import ru.fursa.unsplash.base.repository.UnsplashRepository
import ru.fursa.unsplash.data.ui.models.CollectionModel
import ru.fursa.unsplash.data.ui.models.PhotoModel

class ProfileViewModel(
    private val repository: UnsplashRepository
) : ViewModel() {

    val currentUser: SharedFlow<CurrentUser> = repository.userFlow

    private val _userPhotos = MutableStateFlow<PagingData<PhotoModel>>(PagingData.empty())
    val userPhotos = _userPhotos.asStateFlow()

    private val _userLikedPhotos = MutableStateFlow<PagingData<PhotoModel>>(PagingData.empty())
    val userLikedPhotos = _userLikedPhotos.asStateFlow()

    private val _userCollections = MutableStateFlow<PagingData<CollectionModel>>(PagingData.empty())
    val userCollections = _userCollections.asStateFlow()

    fun getUserProfile(username: String) {
        viewModelScope.launch {
            repository.getUser(username)
        }
    }

    fun getUserPhotos(username: String) {
        viewModelScope.launch {
            repository.getUserPhotos(username)
                .cachedIn(viewModelScope)
                .collectLatest { results ->
                    _userPhotos.value = results
                }
        }
    }

    fun getUserLikedPhotos(username: String) {
        viewModelScope.launch {
            repository.getUserLikes(username)
                .cachedIn(viewModelScope)
                .collectLatest { results ->
                    _userLikedPhotos.value = results
                }
        }
    }

    fun getUserCollections(username: String) {
        viewModelScope.launch {
            repository.getUserCollections(username)
                .cachedIn(viewModelScope)
                .collectLatest { results ->
                    _userCollections.value = results
                }
        }
    }

}