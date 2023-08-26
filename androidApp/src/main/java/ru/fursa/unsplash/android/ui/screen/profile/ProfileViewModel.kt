package ru.fursa.unsplash.android.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ru.fursa.unsplash.base.repository.CurrentUser
import ru.fursa.unsplash.base.repository.UnsplashRepository

class ProfileViewModel(
    private val repository: UnsplashRepository
): ViewModel() {

    val currentUser: SharedFlow<CurrentUser> = repository.userFlow

    fun getUserProfile(username: String) {
        viewModelScope.launch {
            repository.getUser(username)
        }
    }
}