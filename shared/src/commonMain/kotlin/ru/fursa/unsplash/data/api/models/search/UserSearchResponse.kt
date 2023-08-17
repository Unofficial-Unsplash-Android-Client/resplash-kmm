package ru.fursa.unsplash.data.api.models.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.fursa.unsplash.data.api.models.photo.ProfileImage

@Serializable
data class UserSearchResponse(
    @SerialName("first_name")
    val firstName: String? = null,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("profile_image")
    val profileImage: ProfileImage?,
)