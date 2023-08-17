package ru.fursa.unsplash.android.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.ui.controls.collection.CollectionCard

@Composable
fun CollectionSearchScreen(
    viewModel: SearchViewModel = koinViewModel()
) {

    val collectionItems = viewModel.collectionsResults.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {


        LazyColumn(content = {
            items(collectionItems.itemCount) { index ->
                val item = collectionItems[index] ?: return@items
                CollectionCard(
                    url = item.coverPhoto.urls.regularUrl,
                    title = item.title.toString(),
                    totalPhotos = item.totalPhotos,
                    onClick = {})
            }
        })
    }
}