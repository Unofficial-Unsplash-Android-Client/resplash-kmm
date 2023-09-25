package ru.fursa.unsplash.android.ui.screen.home

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.fursa.unsplash.android.base.mvi.UiEffect
import ru.fursa.unsplash.android.base.mvi.UiEvent
import ru.fursa.unsplash.android.base.mvi.UiState
import ru.fursa.unsplash.data.ui.models.PhotoModel

class HomeMVIContract {

    sealed class Event : UiEvent {
        object Loading : Event()
        data class Error(val message: String) : Event()
        data class Loaded(val data: Flow<PagingData<PhotoModel>>) : Event()
        data class OnViewPhoto(val url: String) : Event()
        data class OnUserClick(val username: String) : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val errorMessage: String = "",
        val isSuccess: Boolean = false,
        val data: PagingData<PhotoModel>
    ) : UiState

    sealed class Effect : UiEffect
}
