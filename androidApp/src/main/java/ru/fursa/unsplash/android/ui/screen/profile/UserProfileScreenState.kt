package ru.fursa.unsplash.android.ui.screen.profile

import ru.fursa.unsplash.base.repository.CurrentUser

data class UserProfileScreenState(
    val currentUser: CurrentUser,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String
)