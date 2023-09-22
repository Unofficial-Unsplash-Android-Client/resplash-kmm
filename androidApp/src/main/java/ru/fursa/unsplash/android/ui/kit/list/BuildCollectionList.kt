package ru.fursa.unsplash.android.ui.kit.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import ru.fursa.unsplash.android.ui.kit.compound.CollectionCardItem
import ru.fursa.unsplash.data.ui.models.CollectionModel

@Composable
fun BuildCollectionList(
    collections: LazyPagingItems<CollectionModel>,
    onCollectionClicked: (String, String, Int) -> Unit
) {
    LazyColumn {
        items(count = collections.itemCount) { index ->
            val item = collections[index] ?: return@items
            CollectionCardItem(
                item.coverPhotoUrl,
                item.title,
                item.totalPhotos,
                onNavigateClick = {
                    onCollectionClicked(item.collectionId, item.authorName, item.totalPhotos)
                }
            )
        }
    }
}
