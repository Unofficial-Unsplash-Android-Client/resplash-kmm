package ru.fursa.unsplash.data.api.models.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String?,
    val username: String?,
    @SerialName("instagram_username")
    val instagramUsername: String?,
    @SerialName("profile_image")
    val profileImage: ProfileImage,
    @SerialName("photos")
    val photos: List<Photo>?
)