package ru.fursa.unsplash.android.ui.kit.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import ru.fursa.unsplash.android.ui.kit.compound.PhotoListItem
import ru.fursa.unsplash.data.ui.models.PhotoModel

@Composable
fun HomeList(
    photos: List<PhotoModel>,
    onNavigateClick: (String) -> Unit,
    onViewPhoto: (String) -> Unit,
    onLoadNextItems: () -> Unit,
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState
    ) {
        items(photos.count()) { index ->
            val item = photos[index]
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

            if (index >= photos.size - 1) {
                onLoadNextItems()
            }
        }
    }
}
