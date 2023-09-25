package ru.fursa.unsplash.android.ui.screen.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart
import ru.fursa.unsplash.android.base.mvi.MVIBaseViewModel
import ru.fursa.unsplash.base.repository.UnsplashRepository
import ru.fursa.unsplash.data.ui.models.PhotoModel

class HomeViewModel(
    private val repository: UnsplashRepository
) : MVIBaseViewModel<HomeMVIContract.Event, HomeMVIContract.State, HomeMVIContract.Effect>() {

    val screenState = repository.getPhotos()
        .onStart { }
        .onEmpty { }
        .catch { }
        .cachedIn(viewModelScope)

    override fun createInitialState(): HomeMVIContract.State {
        return HomeMVIContract.State(
            isLoading = true,
            isError = false,
            isSuccess = false,
            data = PagingData.empty<PhotoModel>()
        )
    }

    override fun handleEvent(event: HomeMVIContract.Event) {
        TODO("Not yet implemented")
    }
}
