package ru.fursa.unsplash.data.ui.models

data class UserModel(
    val username: String,
    val profileImageUrl: String,
    val instagramUsername: String,
    val photos: List<String>?
)
