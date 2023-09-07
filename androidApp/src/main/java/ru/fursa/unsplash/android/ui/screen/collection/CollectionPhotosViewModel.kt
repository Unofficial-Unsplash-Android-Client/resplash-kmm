package ru.fursa.unsplash.android.ui.screen.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.fursa.unsplash.base.repository.UnsplashRepository
import ru.fursa.unsplash.data.ui.models.PhotoModel

class CollectionPhotosViewModel(
    private val repository: UnsplashRepository
) : ViewModel() {
    private val _collectionState = MutableStateFlow<PagingData<PhotoModel>>(PagingData.empty())
    val collectionState = _collectionState.asStateFlow()

    fun getCollection(id: String) {
        viewModelScope.launch {
            repository.getCollectionPhotos(id)
                .cachedIn(viewModelScope)
                .collectLatest { result ->
                    _collectionState.value = result
                }
        }
    }
}
