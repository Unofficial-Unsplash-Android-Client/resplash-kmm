package ru.fursa.unsplash.android.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.fursa.unsplash.base.repository.UnsplashRepository
import ru.fursa.unsplash.data.ui.models.CollectionModel
import ru.fursa.unsplash.data.ui.models.PhotoModel
import ru.fursa.unsplash.data.ui.models.UserModel

class SearchViewModel(
    private val repository: UnsplashRepository
) : ViewModel() {

    private val _photoModelResults = MutableStateFlow<PagingData<PhotoModel>>(PagingData.empty())
    val photoResults = _photoModelResults

    private val _collectionsResults = MutableStateFlow<PagingData<CollectionModel>>(PagingData.empty())
    val collectionsResults = _collectionsResults

    private val _userModelResults = MutableStateFlow<PagingData<UserModel>>(PagingData.empty())
    val userResults = _userModelResults

    fun onStartSearch(query: String) {
        viewModelScope.launch {
            repository.searchPhotos(query).cachedIn(viewModelScope).collectLatest { results ->
                _photoModelResults.value = results
            }
        }

        viewModelScope.launch {
            repository.searchCollection(query).cachedIn(viewModelScope).collectLatest { results ->
                _collectionsResults.value = results
            }
        }

        viewModelScope.launch {
            repository.searchUsers(query).cachedIn(viewModelScope).collectLatest { results ->
                _userModelResults.value = results
            }
        }
    }
}
