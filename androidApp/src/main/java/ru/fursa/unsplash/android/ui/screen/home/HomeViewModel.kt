package ru.fursa.unsplash.android.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import ru.fursa.unsplash.base.repository.UnsplashRepository

class HomeViewModel(
    private val repository: UnsplashRepository
): ViewModel() {

    val photosPager = repository.getPhotos().cachedIn(viewModelScope)
}