package ru.fursa.unsplash.base.mappers

import ru.fursa.unsplash.base.Photo
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse

fun PhotoResponse.toUi(): Photo {
    return Photo(
        photoUrl = this.urls.raw,
        username = this.user.name.orEmpty(),
        profileImage = this.user.profileImage.medium.orEmpty(),
    )
}


