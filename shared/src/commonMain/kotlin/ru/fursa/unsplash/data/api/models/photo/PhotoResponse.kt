package ru.fursa.unsplash.data.api.models.photo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(
    val id: String,
    @SerialName("user")
    val user: User,
    @SerialName("urls")
    val urls: Urls
)

@Serializable
data class User(
    val name: String?,
    val username: String?,
    @SerialName("profile_image")
    val profileImage: ProfileImage?,

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