package ru.fursa.unsplash.data.api.models.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse

@Serializable
@SerialName("user")
data class User(
    val name: String?,
    val username: String?,
    @SerialName("instagram_username")
    val instagramUsername: String?,
    @SerialName("profile_image")
    val profileImage: ProfileImage?,
    @SerialName("location")
    val location: String?,
    @SerialName("bio")
    val bio: String?,
    @SerialName("total_likes")
    val totalLikes: Int?,
    @SerialName("total_collections")
    val totalCollections: Int?,
    @SerialName("total_photos")
    val totalPhotos: Int?,
    @SerialName("photos")
    val photoResponses: List<PhotoResponse>?
)
