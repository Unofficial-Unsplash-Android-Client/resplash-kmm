package ru.fursa.unsplash.base.mappers

import ru.fursa.unsplash.base.repository.CurrentUser
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
        fullName = this.user?.username.orEmpty(),
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
            username = response.user?.name.orEmpty(),
            fullName = response.user?.username.orEmpty(),
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
            collectionId = response.id.orEmpty(),
            coverPhotoUrl = response.coverPhoto.urls.rawUrl,
            title = response.title.orEmpty(),
            totalPhotos = response.totalPhotos,
            authorName = response.user?.name.orEmpty()
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
            profileImageUrl = response.profileImage?.medium.orEmpty(),
            instagramUsername = response.instagramUsername.orEmpty(),
            photos = response.photoResponses?.map { it.urls.rawUrl }?.toList() ?: emptyList()
        )
        usersList.add(userModel)
    }

    return usersList
}

fun User.toCurrentUser(): CurrentUser {
    return CurrentUser(
        username = this.username.orEmpty(),
        fullName = this.name.orEmpty(),
        location = this.location.orEmpty(),
        bio = this.bio.orEmpty(),
        totalLikes = this.totalLikes ?: 0,
        totalPhotos = this.totalPhotos ?: 0,
        totalCollections = this.totalCollections ?: 0,
        userAvatarUrl = this.profileImage?.medium.orEmpty()
    )
}
