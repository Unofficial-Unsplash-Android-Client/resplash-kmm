package ru.fursa.unsplash.android.ui.kit.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import ru.fursa.unsplash.android.ui.kit.compound.PhotoListItem
import ru.fursa.unsplash.data.ui.models.PhotoModel

@Composable
fun BuildHomeList(
    photos: LazyPagingItems<PhotoModel>,
    onNavigateClick: (String) -> Unit,
    onViewPhoto: (String) -> Unit,
) {
    LazyColumn {
        items(photos.itemCount) { index ->
            val item = photos[index] ?: return@items
            PhotoListItem(
                url = item.photoUrl,
                username = item.username,
                fullName = item.fullName,
                avatarUrl = item.profileImage,
                onUserClick = { username ->
                    onNavigateClick.invoke(username)
                },
                onViewPhoto = { url ->
                    onViewPhoto.invoke(url)
                }
            )
        }
    }
}
