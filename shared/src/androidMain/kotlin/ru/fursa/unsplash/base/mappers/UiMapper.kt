package ru.fursa.unsplash.base.mappers

import ru.fursa.unsplash.data.api.models.base.User
import ru.fursa.unsplash.data.api.models.collection.CollectionResponse
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse
import ru.fursa.unsplash.data.ui.models.CollectionModel
import ru.fursa.unsplash.data.ui.models.PhotoModel
import ru.fursa.unsplash.data.ui.models.UserModel

private const val DEFAULT_WIDTH = 300
private const val DEFAULT_HEIGHT = 200

fun PhotoResponse.toUiPhoto(): PhotoModel {
    return PhotoModel(
        photoUrl = this.urls.rawUrl,
        username = this.user?.name.orEmpty(),
        profileImage = this.user?.profileImage?.medium.orEmpty(),
        width = this.width ?: DEFAULT_WIDTH,
        height = this.height ?: DEFAULT_HEIGHT
    )
}

fun List<PhotoResponse>.toUiPhotos(): List<PhotoModel> {
    val photosList = mutableListOf<PhotoModel>()
    this.forEach { response ->
        val photoModel = PhotoModel(
            photoUrl = response.urls.rawUrl,
            profileImage = response.user?.profileImage?.medium.orEmpty(),
            username = when {
                response.user?.name != null -> response.user.name
                response.user?.username != null -> response.user.username
                else -> ""
            },
            width = response.width ?: DEFAULT_WIDTH,
            height = response.height ?: DEFAULT_HEIGHT
        )
        photosList.add(photoModel)
    }

    return photosList
}

fun List<CollectionResponse>.toUiCollections(): List<CollectionModel> {
    val collectionModelList = mutableListOf<CollectionModel>()
    this.forEach { response ->
        val collectionModel = CollectionModel(
            coverPhotoUrl = response.coverPhoto.urls.rawUrl,
            title = response.title.orEmpty(),
            totalPhotos = response.totalPhotos
        )
        collectionModelList.add(collectionModel)
    }
    return collectionModelList
}

fun List<User>.toUiUsers(): List<UserModel> {
    val usersList = mutableListOf<UserModel>()
    this.forEach { response ->
        val userModel = UserModel(
            username = response.username.orEmpty(),
            profileImageUrl = response.profileImage.medium,
            instagramUsername = response.instagramUsername.orEmpty(),
            photos = response.photoResponses?.map { it.urls.rawUrl }?.toList() ?: emptyList()
        )
        usersList.add(userModel)
    }

    return usersList
}
