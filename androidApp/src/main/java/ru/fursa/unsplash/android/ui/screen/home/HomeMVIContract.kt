package ru.fursa.unsplash.android.ui.screen.home

import ru.fursa.unsplash.android.base.mvi.UiEffect
import ru.fursa.unsplash.android.base.mvi.UiEvent
import ru.fursa.unsplash.android.base.mvi.UiState
import ru.fursa.unsplash.data.ui.models.PhotoModel

class HomeMVIContract {
    sealed class Event : UiEvent
    data class State(
        val loading: Boolean = false,
        val success: Boolean = false,
        val error: Boolean = false,
        val refresh: Boolean = false,
        val message: String = "",
        val data: List<PhotoModel> = emptyList()
    ) : UiState
    sealed class Effect : UiEffect
}
