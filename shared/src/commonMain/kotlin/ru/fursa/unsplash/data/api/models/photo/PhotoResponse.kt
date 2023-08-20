package ru.fursa.unsplash.data.api.models.photo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(
    val id: String,
    @SerialName("user")
    val user: User?,
    @SerialName("urls")
    val urls: Urls,
    val width: Int,
    val height: Int,
)

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

@Serializable
data class Photo(
    val urls: Urls,
)

@Serializable
data class ProfileImage(
    val small: String,
    val medium: String,
)

@Serializable
data class Urls(
    val raw: String,
    val regular: String,
)