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
    onViewPhoto: (PhotoModel) -> Unit,
) {
    LazyColumn {
        items(photos.itemCount) { index ->
            val item = photos[index] ?: return@items
            PhotoListItem(
                photoModel = item,
                onUserClick = { username ->
                    onNavigateClick.invoke(username)
                },
                onViewPhoto = { selectedPhoto ->
                    onViewPhoto.invoke(selectedPhoto)
                }
            )
        }
    }
}
