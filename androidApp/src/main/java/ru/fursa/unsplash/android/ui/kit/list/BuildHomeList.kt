package ru.fursa.unsplash.android.ui.kit.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import ru.fursa.unsplash.android.ui.kit.compound.PhotoListItem
import ru.fursa.unsplash.data.ui.models.PhotoModel

@Composable
fun BuildHomeList(
    navController: NavController,
    photos: LazyPagingItems<PhotoModel>
) {
    LazyColumn {
        items(photos.itemCount) { index ->
            val item = photos[index] ?: return@items
            PhotoListItem(
                url = item.photoUrl,
                username = item.username,
                avatarUrl = item.profileImage,
                onUserClick = { navController.navigate("profile") }
            )
        }
    }
}