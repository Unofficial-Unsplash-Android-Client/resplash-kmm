package ru.fursa.unsplash.android.ui.screen.home

import androidx.annotation.StringRes
import ru.fursa.unsplash.android.base.mvi.UiEffect
import ru.fursa.unsplash.android.base.mvi.UiEvent
import ru.fursa.unsplash.android.base.mvi.UiState
import ru.fursa.unsplash.data.ui.models.PhotoModel

class HomeMVIContract {
    sealed class Event : UiEvent {
        object Loading : Event()
        data class Error(val message: String) : Event()
        data class Success(val data: List<PhotoModel>) : Event()
    }

    data class State(
        val loading: Boolean = false,
        val success: Boolean = false,
        val error: Boolean = false,
        val message: String = "",
        val data: List<PhotoModel> = emptyList()
    ): UiState

    sealed class Effect : UiEffect {
        data class ShowToastMessage(@StringRes val message: Int) : Effect()
    }
}