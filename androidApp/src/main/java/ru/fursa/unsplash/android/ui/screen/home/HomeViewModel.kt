package ru.fursa.unsplash.android.ui.screen.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.fursa.unsplash.base.repository.UnsplashRepository
import ru.fursa.unsplash.data.ui.models.PhotoModel

class HomeViewModel(
    private val repository: UnsplashRepository
) : ViewModel() {

    var uiState by mutableStateOf(HomeScreenState())
    private var currentPage = 1

    fun loadItems(forceReload: Boolean = false) {
        if (uiState.loading) return
        if (forceReload) currentPage = 1
        if (currentPage == 1) uiState = uiState.copy(refreshing = true)

        viewModelScope.launch {
            uiState = uiState.copy(
                loading = true
            )

            try {
                val resultItems = repository.getPhotos(pageIndex = currentPage)
                val items = if (currentPage == 1) resultItems else uiState.items + resultItems
                currentPage += 1
                Log.d("Home", "Page index = $currentPage")

                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = resultItems.isEmpty(),
                    items = items
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = true,
                    errorMessage = "Could not load data: ${e.localizedMessage}"
                )
            }
        }
    }

    data class HomeScreenState(
        val loading: Boolean = false,
        val refreshing: Boolean = false,
        var items: List<PhotoModel> = listOf(),
        val errorMessage: String? = null,
        var loadFinished: Boolean = false,
    )
}
