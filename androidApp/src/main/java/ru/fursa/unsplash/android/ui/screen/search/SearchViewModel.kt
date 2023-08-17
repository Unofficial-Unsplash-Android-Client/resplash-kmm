package ru.fursa.unsplash.android.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.fursa.unsplash.base.repository.UnsplashRepository
import ru.fursa.unsplash.data.api.models.collection.CollectionResponse
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse
import ru.fursa.unsplash.data.api.models.search.UserSearchResponse

class SearchViewModel(
    private val repository: UnsplashRepository
) : ViewModel() {

    private val _photoResults = MutableStateFlow<PagingData<PhotoResponse>>(PagingData.empty())
    val photoResults = _photoResults

    private val _collectionsResults = MutableStateFlow<PagingData<CollectionResponse>>(PagingData.empty())
    val collectionsResults = _collectionsResults

    private val _userResults = MutableStateFlow<PagingData<UserSearchResponse>>(PagingData.empty())
    val userResults = _userResults

    fun onStartSearch(query: String) {
        viewModelScope.launch {
            repository.searchPhotos(query).cachedIn(viewModelScope).collectLatest { results ->
                _photoResults.value = results
            }
        }

        viewModelScope.launch {
            repository.searchCollection(query).cachedIn(viewModelScope).collectLatest { results ->
                _collectionsResults.value = results
            }
        }

        viewModelScope.launch {
            repository.searchUsers(query).cachedIn(viewModelScope).collectLatest { results ->
                _userResults.value = results
            }
        }
    }
}

