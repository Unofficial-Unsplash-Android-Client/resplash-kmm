package ru.fursa.unsplash.android.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.base.encodeUrl
import ru.fursa.unsplash.android.ui.kit.compound.PhotoListItem
import ru.fursa.unsplash.routing.Routes

@Composable
fun PhotosSearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    navController: NavController
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
                PhotoListItem(
                    url = item.photoUrl,
                    username = item.username,
                    avatarUrl = item.profileImage,
                    fullName = item.fullName,
                    width = item.width,
                    height = item.height,
                    onUserClick = { username ->
                        navController.navigate("${Routes.Profile.name}/$username")
                    },
                    onViewPhoto = { url ->
                        navController.navigate("${Routes.View.name}/${url.encodeUrl()}")
                    },
                )
            }
        })
    }
}
