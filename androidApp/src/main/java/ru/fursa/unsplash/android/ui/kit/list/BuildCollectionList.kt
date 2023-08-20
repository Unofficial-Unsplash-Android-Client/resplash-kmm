package ru.fursa.unsplash.android.ui.kit.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import ru.fursa.unsplash.android.ui.kit.compound.CollectionCardItem
import ru.fursa.unsplash.data.api.models.collection.CollectionResponse

@Composable
fun BuildCollectionList(
    collections: LazyPagingItems<CollectionResponse>,
    navController: NavController
) {
    LazyColumn {
        items(count = collections.itemCount) { index ->
            val item = collections[index] ?: return@items
            CollectionCardItem(
                item.coverPhoto.urls.rawUrl,
                item.title.orEmpty(),
                item.totalPhotos,
                onNavigateClick = { navController.navigate("profile") }
            )
        }
    }
}