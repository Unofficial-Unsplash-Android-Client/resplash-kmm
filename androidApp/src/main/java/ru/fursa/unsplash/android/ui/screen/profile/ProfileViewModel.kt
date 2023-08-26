package ru.fursa.unsplash.android.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.fursa.unsplash.base.repository.CurrentUser
import ru.fursa.unsplash.base.repository.Tab
import ru.fursa.unsplash.base.repository.UnsplashRepository

class ProfileViewModel(
    private val repository: UnsplashRepository
): ViewModel() {

    val currentUser: SharedFlow<CurrentUser> = repository.userFlow
    val tabs: StateFlow<List<Tab>> = repository.tabFlow

    fun getUserProfile(username: String) {
        viewModelScope.launch {
            repository.getUser(username)
        }
    }

}