package ru.fursa.unsplash.android.ui.screen.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.fursa.unsplash.android.base.mvi.MVIBaseViewModel
import ru.fursa.unsplash.base.repository.UnsplashRepository

class HomeViewModel(
    private val repository: UnsplashRepository
) : MVIBaseViewModel<HomeMVIContract.Event, HomeMVIContract.State, HomeMVIContract.Effect>() {

    private var page = 1
    override fun createInitialState(): HomeMVIContract.State {
        return HomeMVIContract.State()
    }
    override fun handleEvent(event: HomeMVIContract.Event) = Unit

    fun loadItems() {
        if (uiState.value.loading) return
        if (page == 1) setState { copy(loading = true) }

        viewModelScope.launch {
            setState { copy(loading = true) }

            try {
                val resultItems = repository.getPhotos(pageIndex = page)
                val items = if (page == 1) resultItems else uiState.value.data + resultItems
                page++
                setState {
                    copy(
                        loading = false,
                        refresh = false,
                        success = items.isNotEmpty(),
                        data = items
                    )
                }
            } catch (e: Throwable) {
                setState {
                    copy(
                        loading = false,
                        refresh = false,
                        error = true,
                        message = "Could not load items: ${e.localizedMessage}"
                    )
                }
            }
        }
    }
}
