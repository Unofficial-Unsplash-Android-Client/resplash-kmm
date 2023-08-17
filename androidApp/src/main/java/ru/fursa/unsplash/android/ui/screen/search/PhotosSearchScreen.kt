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
import ru.fursa.unsplash.android.ui.controls.photo.PhotoCard

@Composable
fun PhotosSearchScreen(
    viewModel: SearchViewModel = koinViewModel()
) {

    val searchResults = viewModel.photoResults.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {


        LazyColumn(content = {
            items(searchResults.itemCount) { index ->
                val item = searchResults[index] ?: return@items
                PhotoCard(
                    url = item.urls.regular,
                    username = item.user.username.orEmpty(),
                    profileImage = item.user.profileImage?.small.orEmpty()
                ) {

                }
            }
        })
    }
}

