package ru.fursa.unsplash.android.ui.screen.collections

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import ru.fursa.unsplash.api.CollectionHttpResponse
import ru.fursa.unsplash.api.UnsplashApiService

class CollectionViewModel(
    private val apiService: UnsplashApiService
): ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.EmptyState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadCollections() {
        viewModelScope.launch {
            val collections = apiService.getCollections(pageIndex = 3)
            _uiState.value = UiState.SuccessState(collections)
        }
    }

    sealed class UiState {
        object EmptyState: UiState()
        data class ErrorState(val message: String): UiState()
        data class SuccessState(val data: List<CollectionHttpResponse>): UiState()
    }
}

