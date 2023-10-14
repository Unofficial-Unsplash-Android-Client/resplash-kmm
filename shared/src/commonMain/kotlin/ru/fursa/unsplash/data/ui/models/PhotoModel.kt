package ru.fursa.unsplash.data.ui.models

data class PhotoModel(
    val photoUrl: String,
    val username: String,
    val fullName: String,
    val profileImage: String,
    val width: Int,
    val height: Int,
    val likesCount: Long,
    val watchCount: Long,
    val downloadCount: Long,
)
