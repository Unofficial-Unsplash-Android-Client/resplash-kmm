package ru.fursa.unsplash.android.ui.screen.collections

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.fursa.unsplash.android.base.eventbus.LoadEventBus
import ru.fursa.unsplash.base.repository.UnsplashRepository

class CollectionViewModel(
    private val repository: UnsplashRepository,
    private val searchEventBus: LoadEventBus
) : ViewModel() {
    val collectionPager = repository.getCollections().cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            searchEventBus.event.collectLatest { event ->
                when(event) {
                    is LoadEventBus.Event.Search -> {
                        Log.d("Unsplash", "Collections search -> ${event.query}")
                    }

                    else -> {}
                }
            }
        }
    }
}

