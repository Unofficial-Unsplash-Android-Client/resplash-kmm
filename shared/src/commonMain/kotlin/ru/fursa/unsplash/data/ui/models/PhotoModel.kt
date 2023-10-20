package ru.fursa.unsplash.data.ui.models

data class PhotoModel(
    val id: String,
    val photoUrl: String,
    val username: String,
    val fullName: String,
    val profileImage: String,
    val width: Int,
    val height: Int,
)
