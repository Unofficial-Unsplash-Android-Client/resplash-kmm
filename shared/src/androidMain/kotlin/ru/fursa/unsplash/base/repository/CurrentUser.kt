package ru.fursa.unsplash.base.repository

data class CurrentUser(
    val username: String,
    val fullName: String,
    val location: String,
    val bio: String,
    val totalLikes: Int,
    val totalPhotos: Int,
    val totalCollections: Int,
    val userAvatarUrl: String,
)
