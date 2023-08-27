package ru.fursa.unsplash.base.repository

data class CurrentUser(
    val username: String = "",
    val fullName: String = "",
    val location: String = "",
    val bio: String = "",
    val totalLikes: Int = 0,
    val totalPhotos: Int = 0,
    val totalCollections: Int = 0,
    val userAvatarUrl: String = "",
)
